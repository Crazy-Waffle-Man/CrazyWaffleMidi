# Crazy_Waffle_Midi
This is a really scuffed midi sequencer. It's currently in the very early stages, so don't expect much for a few weeks. As of this commit, it supports creating, selecting, moving, and resizing notes, with horizontal snap by quarter notes (This can be disabled by setting `snapEnabled` to false in `./src/main/java/com/cwm/CrazyWaffleMidi/ui/widgets/NoteCanvas.java`).

## Usage
To run it and have it guaranteed to work, you need to have a JDK of some variety. There are native Windows, Linux, and MacOS builds available, but I'm only certain that the Linux build works. For the Linux build, you must  have Qt6.12.0 installed. I would have made this a dependency of the binary, but I wanted this build to be distro-agnostic, so I won't make a `.deb` (Debian-based distros only) or `PKGBUILD` (Arch-based only).

1. Download this repository (either via the zip download/extract or with `git clone https://github.com/Crazy-Waffle-Man/CrazyWaffleMidi.git`) and navigate to the folder on your device.
2. Right click on the background of your file explorer and press "Open in terminal"
3. Run the app by typing `./run`

## Disclosures
This app uses Qt6.12.0 as a dependency. None of the code in Qt6.12.0 is used or modified here. It is distributed under the GPL 3.0 license (I think) and its source code can be found [here](https://github.com/qt/qtbase/tree/6.12.0).


I used a large language model for minor fixes in the macOS build script and to translate the build scripts to Powershell. There is no AI use in the actual app.