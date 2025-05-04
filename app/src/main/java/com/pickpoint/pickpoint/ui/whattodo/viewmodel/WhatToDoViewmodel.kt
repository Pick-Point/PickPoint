package com.pickpoint.pickpoint.ui.whattodo.viewmodel

import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.template.model.Social
import com.kakao.sdk.template.model.TextTemplate
import com.pickpoint.pickpoint.ui.common.util.getRandomElements
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WhatToDoViewmodel : ViewModel() {

    private val _count: MutableStateFlow<Int> = MutableStateFlow(4)
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _resultList: MutableStateFlow<List<String>> = MutableStateFlow(
        mutableListOf(
            "", "", "", ""
        )
    )
    val resultList: StateFlow<List<String>> = _resultList.asStateFlow()

    private val _isConfirmed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isConfirmed: StateFlow<Boolean> = _isConfirmed.asStateFlow()

    private val _randomColors: MutableStateFlow<List<Color>> = MutableStateFlow(emptyList())
    val randomColors: StateFlow<List<Color>> = _randomColors.asStateFlow()

    fun setConfirmed(value: Boolean) {
        _isConfirmed.value = value
    }

    fun initRandomColors(colors : List<Color>){
        _randomColors.value = colors
    }

    fun onConfirmButtonClick() {
        _isConfirmed.value = true
        _randomColors.value = randomColors.value.getRandomElements(count = count.value)
    }

    fun onPlusButtonClick() {
        if (_count.value < 10) {
            _count.value++
            _resultList.value = _resultList.value.toMutableList().apply { add("") }
        }
    }

    fun onMinusButtonClick() {
        if (_count.value > 1) {
            _count.value--
            _resultList.value = _resultList.value.toMutableList().apply { removeAt(_count.value) }
        }
    }

    fun updateResultIndex(index: Int, result: String) {
        _resultList.value = _resultList.value.toMutableList().apply { set(index, result) }
    }

    fun reset() {
        _count.value = 4
        _resultList.value = mutableListOf("", "", "", "")
    }

    fun onSettingClick() {
        _isConfirmed.value = false
        _randomColors.value = randomColors.value.getRandomElements(count = count.value)
    }

    // 텍스트 템플릿으로 공유하기 (간단한 텍스트만 필요한 경우)
    fun shareTextToKakaoTalk(context: Context) {
        val resultList = resultList.value

        // 결과 텍스트 포맷팅
        val formattedResults = resultList.mapIndexed { index, result ->
            "${index + 1}. $result"
        }.joinToString("\n")

        // 텍스트 템플릿 생성
        val textTemplate = TextTemplate(
            text = "What To Do 결과:\n$formattedResults",
            link = Link(
                webUrl = "https://play.google.com/store/apps/details?id=com.pickpoint.pickpoint",
                mobileWebUrl = "https://play.google.com/store/apps/details?id=com.pickpoint.pickpoint"
            ),
            // 버튼 추가
            buttonTitle = "앱 설치 해보기",
        )

        // 카카오톡 설치 여부 확인
        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            // 카카오톡으로 공유
            ShareClient.instance.shareDefault(context, textTemplate) { result, error ->
                if (error != null) {
                    Log.e("KakaoShare", "카카오톡 공유 실패", error)
                } else if (result != null) {
                    Log.d("KakaoShare", "카카오톡 공유 성공 ${result.intent}")
                    context.startActivity(result.intent)
                }
            }
        } else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            // 웹 공유 예시 코드
            val sharerUrl = WebSharerClient.instance.makeDefaultUrl(textTemplate)

            // CustomTabs으로 웹 브라우저 열기

            // 1. CustomTabsServiceConnection 지원 브라우저 열기
            // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
            try {
                KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
            } catch(e: UnsupportedOperationException) {
                // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabsServiceConnection 미지원 브라우저 열기
            // ex) 다음, 네이버 등
            try {
                KakaoCustomTabsClient.open(context, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                // 디바이스에 설치된 인터넷 브라우저가 없을 때 예외처리
            }
        }
    }
}