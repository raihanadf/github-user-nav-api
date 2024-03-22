plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("androidx.navigation.safeargs.kotlin")
}

android {
	namespace = "com.raihan.githubapp"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.raihan.githubapp"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildFeatures {
		buildConfig = true
		viewBinding = true
	}

	buildTypes {
		debug {
			buildConfigField("String", "API_URL", "\"https://api.github.com/\"")
			buildConfigField("String", "API_KEY", "\"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\"")
		}
		release {
			buildConfigField("String", "API_URL", "\"https://api.github.com/\"")
			buildConfigField("String", "API_KEY",
				"\"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\"")
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	// required for this project
	implementation(libs.retrofit) // [[ network: retrofit ]]
	implementation(libs.moshi.kotlin) // [[ network: moshi ]]
	implementation(libs.converter.moshi) // [[ network: moshi converter ]]
	implementation(libs.coil) // [[ network: coil ]]
	implementation(libs.androidx.lifecycle.livedata.ktx) // [[ lifecycle: livedata ]]
	implementation(libs.androidx.lifecycle.viewmodel.ktx) // [[ lifecycle: viewmodel ]]
	implementation(libs.androidx.navigation.fragment.ktx) // [[ navigation ]]
	implementation(libs.androidx.navigation.ui.ktx) // [[ navigation ]]
	implementation(libs.androidx.viewpager2) // [[ viewpager ]]
	implementation(libs.androidx.core.splashscreen) // [[ splash screen ]]
	implementation(libs.circleimageview) // [[ circle image ]]

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}