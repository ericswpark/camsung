# Changelog

<div align="center">

[한국어][korean-translation]

</div>

The format is based on [Keep a Changelog][keep-a-changelog].

[korean-translation]: CHANGELOG.ko.md
[keep-a-changelog]: https://keepachangelog.com/en/1.0.0/

# [Unreleased]

[Unreleased]: https://github.com/ericswpark/camsung/compare/1.1.0...HEAD

# [1.1.0] - 2025-04-28

## Added

- App shortcuts and intents have been added! You can now automate the camera mute setting with Galaxy Actions or Tasker.

## Changed

- New app icon and color scheme
- General code cleanup
- Better permissions check and state updating
- Updated library dependencies
- Screen rotations are now enabled for proper window proportions in windowed mode
- Fixed the app ratio not being resizable due to the low build SDK version

[1.1.0]: https://github.com/ericswpark/camsung/compare/1.0.3...1.1.0

# [1.0.3] - 2023-03-12

## Changed

- Fixed error message about insufficient permissions on boot, even when permissions have been granted
- Fixed an intent check that could allow third-party apps to silence the camera through camsung
- General code cleanup

# [1.0.2] - 2021-02-02

## Changed

- Changed color scheme

# [1.0.1] - 2021-02-01

## Changed

- Fixes crash if the prop does not exist

# [1.0.0] - 2021-02-01

Initial release

[1.0.3]: https://github.com/ericswpark/camsung/compare/1.0.2...1.0.3
[1.0.2]: https://github.com/ericswpark/camsung/compare/1.0.1...1.0.2
[1.0.1]: https://github.com/ericswpark/camsung/compare/1.0.0...1.0.1
[1.0.0]: https://github.com/ericswpark/camsung/compare/509b2f1e5b6dbbee4b2436d20d0b61c04de728bc...1.0.0
