# 🏗 Namma-Mistri — ನಮ್ಮ ಮಿಸ್ತ್ರಿ

### Android App Development using GenAI
**MindMatrix VTU Internship Program — Project #27**

---

## 👩‍💻 Developed By
- **Name:** Lisha Maria Rodrigues
- **USN:** 4MW22CS085
- **College:** Shri Madhwa Vadiraja Institute of Technology and Management, Bantakal
- **Internship:** MindMatrix.io (CL Infotech Pvt. Ltd.), Bangalore

---

## 📱 About the App

Namma-Mistri is a digital construction assistant for rural masons (Mistris).
It helps them accurately estimate construction materials and manage
daily labor operations — solving real problems in rural India.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🧱 Material Calculator | Estimates bricks, cement, sand using civil engineering formulas |
| 👷 Labor Diary | Worker attendance, advance payments, auto balance calculation |
| 📸 Site Photos | Gallery-based photo management for construction progress |
| 💰 Standard Rates | Editable local material price list |
| 🏠 Site Management | Multiple active sites with separate records |
| 🌙 Dark Mode | Material 3 theming with light/dark toggle |

---

## 🛠 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose + Material 3
- **Architecture:** MVVM + Clean Architecture
- **Database:** Room Database
- **Navigation:** Navigation Component
- **Image Loading:** Coil
- **Min SDK:** API 24 (Android 7.0+)
- **IDE:** Android Studio

---

## 🏗 Architecture
UI Layer (Jetpack Compose Screens)
↓
ViewModel Layer (StateFlow + Business Logic)
↓
Repository Layer (Single Source of Truth)
↓
Room Database (Local Persistent Storage)
---

## 📐 Material Calculator Formula
Wall Volume  = Length(m) × Height(m) × Thickness(m)
Bricks       = Volume × (1 / BrickVolume) × 1.3 (wastage factor)
Cement Bags  = Volume × 1.5 (1:6 mortar mix)
Sand Loads   = Volume × 0.9
---

## ▶ How to Run

1. Clone this repository
2. Open in Android Studio
3. Sync Gradle
4. Run on Android device (API 24+)

---

## 📋 PRD Success Criteria — All Met ✅

- [x] Material calculator supports different wall thicknesses
- [x] Labor diary calculates balance automatically
- [x] Room DB correctly stores all data
- [x] UI is simple, rugged, and easy to use
- [x] App runs smoothly without crashes
