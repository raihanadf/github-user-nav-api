plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	id("androidx.navigation.safeargs.kotlin")
	id("com.google.devtools.ksp")
	id("kotlin-parcelize")
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
			buildConfigField(
				"String",
				"API_KEY",
				"\"\""
			)
		}
		release {
			buildConfigField("String", "API_URL", "\"https://api.github.com/\"")
			buildConfigField(
				"String", "API_KEY",
				"\"\""
			)
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

	// database
	implementation(libs.androidx.room.ktx)
	implementation(libs.core.ktx)
	ksp(libs.androidx.room.compiler)

	// datastore
	implementation(libs.androidx.datastore.preferences)
	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.coroutines.android)

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)

	// testing additional
	implementation(libs.androidx.espresso.contrib)
	implementation(libs.androidx.fragment.testing)

	// junit and mockito
	testImplementation(libs.junit)

	// espresso
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.runner)
	androidTestImplementation(libs.androidx.rules)
}