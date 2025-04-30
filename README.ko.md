# 캠성

[![IzzyOnDroid](https://img.shields.io/endpoint?url=https://apt.izzysoft.de/fdroid/api/v1/shield/android.com.ericswpark.camsung)](https://apt.izzysoft.de/fdroid/index/apk/android.com.ericswpark.camsung)

삼성 폰에서 카메라 무음 설정

[최신 빌드 다운로드][release-latest-apk]

[release-latest-apk]: https://github.com/ericswpark/camsung/releases/latest/download/app-release.apk

## 스샷

![메인 윈도우](fastlane/metadata/android/ko-KR/images/phoneScreenshots/1.png?raw=true)

## 설치

만약 폰의 안드로이드 버전이 13 이하라면 APK를 그대로 설치하실 수 있습니다.

만약 폰의 안드로이드 버전이 14 이상이면 구글이 SDK 버전 22를 타겟팅하는 어플의 설치를 막기 때문에 ADB를 통하여 어플을 설치해야 됩니다. 개발자 모드를 활성화한 후 USB 디버깅을 켠 다음, 다음을 실행하세요:

```
adb install --bypass-low-target-sdk-block app-release.apk
```

만약 파일 이름을 변경하셨다면 `app-release.apk`를 바꿔주세요.

더 자세한 설치 방법은 [나무위키에도 등재되어 있습니다](https://namu.wiki/w/%EC%BA%A0%EC%84%B1?from=camsung#s-4)!

## 사용법

설치 후 실행한 다음, 스위치를 눌러 무음을 활성화합니다. 잠금 아이콘을 누르면 부팅시 매번 적용합니다.

카메라 무음이 활성화되려면 시스템 소리 모드가 진동이나 무음으로 설정되어 있어야 됩니다!

## 제거

스위치를 끄고 설치 삭제하시면 됩니다. 만약 스위치를 끄기 전에 삭제하셨다면, 어플을 다시 설치한 후, 스위치를 몇번 토글하여 끔 상태로
만든 후 (예를 들어, 만약 스위치가 꺼져있다면 킨 다음 다시 끄고, 켜져있다면 그냥 끄시면 됩니다), 어플 설치를 삭제하시면 됩니다.

만약 제거 방법들을 모두 시도했는데도 예상치 않게 작동한다면, 컴퓨터에 휴대폰을 USB로 연결하고, ADB 개발자 모드를 활성화한 후 다음 명령을 실행하세요:

```
adb shell settings delete system csc_pref_camera_forced_shuttersound_key
```

이 명령은 캠성이 사용하는 설정값을 삭제합니다.

## 경고

악의적인 용도로의 사용을 자제해주세요. 이 어플은 조용히 해야 하는 상황(예를 들어 음식점이나 도서관)에서
남을 방해하지 않고 카메라를 사용할 수 있도록 제작되었습니다.

이 어플은 시스템 설정을 직접 변경합니다. 비교적 안전하게 만들어졌지만 아직도 버그나 오류가 있을 수 있으니 사용할 때 조심하세요. 이
어플을 사용하면서 발생하는 문제에 대해 책임을 지지 않습니다.

이 어플은 최근에 출시된 안드로이드 버전과 One UI 릴리스에서 작동합니다. 추후 소프트웨어 업데이트로 음소거 기능이 막힐 수 있습니다.
