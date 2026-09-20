<div align="center">

# The Aether II (Fabric 26.3)

[![Minecraft 26.3](https://img.shields.io/badge/Minecraft-26.3-blue?style=for-the-badge&logo=minecraft)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Modloader-Fabric-dbb879?style=for-the-badge&logo=fabric)](https://fabricmc.net/)
[![Java 25](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/25/)
[![License: LGPL v3.0](https://img.shields.io/badge/Code_License-LGPL_v3.0-green.svg?style=for-the-badge)](https://www.gnu.org/licenses/lgpl-3.0.html)
[![Assets](https://img.shields.io/badge/Assets-All_Rights_Reserved-red.svg?style=for-the-badge)](https://en.wikipedia.org/wiki/All_rights_reserved)

*A community port of **The Aether II** to Minecraft 26.3 powered by the Fabric modloader.*

</div>

---

## 📖 Overview

**The Aether II** is the sequel to the legendary dimension mod, *The Aether*. Ascend to the Hostile and Holy Isles of the Aether dimension, featuring new biomes, unique mobs, mysterious dungeons, expansive progression systems, and custom world generation.

This branch (`26.3-fabric`) ports the mod codebase to **Minecraft 26.3** on **Fabric**, updating the rendering pipelines, mixins, world generation features, item capabilities, and resource management.

### ✨ Key Features in this Port
- **Minecraft 26.3 & Fabric Compatibility**: Fully ported to modern Fabric Loader (`0.19.5+`) and Fabric API (`0.161.0+26.3`).
- **Java 25 Ready**: Utilizes Java 25 toolchain and modern language features.
- **Full Localization**: Includes complete English (`en_us`) and Russian (`ru_ru`) localizations.
- **Working Datagen**: Fully operational data generation (`runDatagen`) pipeline.

---

## 🛠️ Prerequisites

Before building, ensure you have:
1. **JDK 25** (Java Development Kit 25) installed and configured in your `PATH` / `JAVA_HOME`.
   - Recommended: OpenJDK 25 or Eclipse Temurin / Oracle JDK 25.
2. **Git** for cloning the repository.

---

## 📦 Setting Up Dependencies (mavenLocal)

This project requires **Nitrogen** and **Cumulus** libraries ported for Fabric 26.3. These dependencies are resolved via `mavenLocal()`.

Download the precompiled artifacts from the GitHub releases:
- **Nitrogen 26.3 (Fabric)**: [Release v1.0.0](https://github.com/roggy666/Nitrogen-26.2-Fabric/releases/tag/v1.0.0)
  - `nitrogen_internals-fabric-26.3-1.3.5-fabric.jar`
- **Cumulus 26.3 (Fabric)**: [Release v1.0.0](https://github.com/roggy666/Cumulus-26.2/releases/tag/v1.0.0)
  - `cumulus_menus-26.3-2.0.15-fabric.jar`

> [!NOTE]
> All other dependencies (Fabric Loader, Fabric API, Forge Config API Port, JEI, Sodium) are automatically fetched from public repositories. No other manual links or files are required!

### Automated Setup

#### Option A: PowerShell (Windows)
Run the following script to automatically download and install the jars into your local Maven cache (`~/.m2/repository`):

```powershell
# 1. Install Nitrogen
$nitrogenDir = "$HOME/.m2/repository/com/aetherteam/nitrogen/nitrogen_internals-fabric/26.3-1.3.5-fabric"
New-Item -ItemType Directory -Force -Path $nitrogenDir | Out-Null
Invoke-WebRequest -Uri "https://github.com/roggy666/Nitrogen-26.2-Fabric/releases/download/v1.0.0/nitrogen_internals-fabric-26.3-1.3.5-fabric.jar" -OutFile "$nitrogenDir/nitrogen_internals-fabric-26.3-1.3.5-fabric.jar"

# 2. Install Cumulus
$cumulusDir = "$HOME/.m2/repository/com/aetherteam/cumulus/cumulus_menus/26.3-2.0.15-fabric"
New-Item -ItemType Directory -Force -Path $cumulusDir | Out-Null
Invoke-WebRequest -Uri "https://github.com/roggy666/Cumulus-26.2/releases/download/v1.0.0/cumulus_menus-26.3-2.0.15-fabric.jar" -OutFile "$cumulusDir/cumulus_menus-26.3-2.0.15-fabric.jar"

Write-Host "Local Maven dependencies installed successfully!" -ForegroundColor Green
```

#### Option B: Bash (Linux / macOS)
```bash
# 1. Install Nitrogen
NITROGEN_DIR="$HOME/.m2/repository/com/aetherteam/nitrogen/nitrogen_internals-fabric/26.3-1.3.5-fabric"
mkdir -p "$NITROGEN_DIR"
curl -L "https://github.com/roggy666/Nitrogen-26.2-Fabric/releases/download/v1.0.0/nitrogen_internals-fabric-26.3-1.3.5-fabric.jar" -o "$NITROGEN_DIR/nitrogen_internals-fabric-26.3-1.3.5-fabric.jar"

# 2. Install Cumulus
CUMULUS_DIR="$HOME/.m2/repository/com/aetherteam/cumulus/cumulus_menus/26.3-2.0.15-fabric"
mkdir -p "$CUMULUS_DIR"
curl -L "https://github.com/roggy666/Cumulus-26.2/releases/download/v1.0.0/cumulus_menus-26.3-2.0.15-fabric.jar" -o "$CUMULUS_DIR/cumulus_menus-26.3-2.0.15-fabric.jar"

echo "Local Maven dependencies installed successfully!"
```

#### Option C: Maven CLI (Alternative)
If you have Maven (`mvn`) installed:
```bash
mvn install:install-file -Dfile=nitrogen_internals-fabric-26.3-1.3.5-fabric.jar -DgroupId=com.aetherteam.nitrogen -DartifactId=nitrogen_internals-fabric -Dversion=26.3-1.3.5-fabric -Dpackaging=jar
mvn install:install-file -Dfile=cumulus_menus-26.3-2.0.15-fabric.jar -DgroupId=com.aetherteam.cumulus -DartifactId=cumulus_menus -Dversion=26.3-2.0.15-fabric -Dpackaging=jar
```

---

## 🔨 Building from Source

Once the dependencies are installed into `mavenLocal`:

### 1. Build the Mod JAR
```bash
# Windows
.\gradlew build

# Linux / macOS
./gradlew build
```
The compiled mod JAR will be located in `build/libs/aether_ii-fabric-26.3-1.0-alpha.4.1-fabric.jar`.

### 2. Run Minecraft Client in Development
To launch the client and test directly from source:
```bash
# Windows
.\gradlew runClient

# Linux / macOS
./gradlew runClient
```

### 3. Run Data Generation (Datagen)
To regenerate recipes, tags, blockstates, or models:
```bash
# Windows
.\gradlew runDatagen

# Linux / macOS
./gradlew runDatagen
```

---

## 🌐 Localization (Локализация)

- **English (`en_us`)**: Complete original texts.
- **Russian (`ru_ru`)**: Fully localized (1,688 strings covering all items, blocks, lore, equipment, biomes, entities, GUI elements, and advancments).

---

## 📜 Credits and Licensing

- **Original Creators**: [The Aether Team](https://github.com/The-Aether-Team). Support the official team on [Patreon](https://patreon.com/TheAetherTeam) and visit the [Official Aether Discord](https://discord.gg/aethermod).
- **Code License**: Licensed under the [GNU Lesser General Public License v3.0 (LGPL v3.0)](https://www.gnu.org/licenses/lgpl-3.0.html).
- **Asset License**: All rights reserved by The Aether Team and respective authors.
