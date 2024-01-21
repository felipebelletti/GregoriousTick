# GregoriousTick (Minecraft Forge 1.7.10)

## Description
GregoriousTick is a Minecraft mod designed for Minecraft version 1.7.10. It provides functionality to control the daylight cycle in the game based on player activity. When the last player logs out of the server, the mod stops the daylight cycle. When a player logs in, the daylight cycle is resumed.

## Motivations
'GregoriousTick' is a nod to the  [GregTech New Horizons (GTNH) modpack](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack), which had an issue with the in-game day counter continuously increasing even when no players were online on a dedicated server. This mod was created to address it.

## Requirements
- Minecraft Forge 1.7.10
- Java Development Kit (JDK) 8

## Features
- Automatically stops the daylight cycle when no players are online.
- Resumes the daylight cycle when at least one player is online.

## Setup
1. Clone the repository
2. Run the Gradle build command:
`.\gradlew build`
3. The built mod JAR file will be located in `build/libs`

## Special Thanks
Special thanks to [Aizistral-Studios](https://github.com/Aizistral-Studios/ForgeWorkspaceSetup) for their ForgeWorkspaceSetup.
This workspace setup provided an enhanced foundation for Forge 1.7.10 mod development, addressing issues for running Forge on 1.7.10 in 2024, eight years after its release.