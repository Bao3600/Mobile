<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_marginBottom="10dp"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:text="FoodBuddy"
        android:textColor="@color/teal_200"
        android:textSize="40sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <View
        android:id="@+id/view"
        android:layout_width="210dp"
        android:layout_height="450dp"
        android:background="@drawable/ic_login_bg"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.cardview.widget.CardView
        android:id="@+id/cardView"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="48dp"
        android:layout_marginEnd="48dp"
        app:cardCornerRadius="24dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <com.google.android.material.textfield.TextInputLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_marginTop="6dp"
                android:layout_marginBottom="12dp"
                app:boxBackgroundColor="@color/white">

                <com.google.android.material.textfield.TextInputEditText
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:drawableStart="@drawable/ic_email"
                    android:drawablePadding="10dp"
                    android:hint="Email"
                    android:inputType="textEmailAddress"
                    android:paddingStart="6dp"
                    android:paddingEnd="6dp" />
            </com.google.android.material.textfield.TextInputLayout>

            <com.google.android.material.textfield.TextInputLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="6dp"
                android:layout_marginBottom="12dp"
                app:boxBackgroundColor="@color/white"
                app:passwordToggleEnabled="true">

                <com.google.android.material.textfield.TextInputEditText
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:drawableStart="@drawable/ic_lock"
                    android:drawablePadding="10dp"
                    android:hint="Password"
                    android:inputType="textPassword"
                    android:paddingStart="6dp"
                    android:paddingEnd="6dp" />
            </com.google.android.material.textfield.TextInputLayout>
        </LinearLayout>
    </androidx.cardview.widget.CardView>

    <Button
        android:id="@+id/button"
        android:layout_width="180dp"
        android:layout_height="wrap_content"
        android:background="@drawable/login_btn_bg"
        android:text="LOGIN"
        android:textSize="16sp"
        android:textStyle="bold"
        app:backgroundTint="?attr/colorControlActivated"
        app:layout_constraintBottom_toBottomOf="@+id/cardView"
        app:layout_constraintEnd_toEndOf="@+id/cardView"
        app:layout_constraintStart_toStartOf="@+id/cardView"
        app:layout_constraintTop_toBottomOf="@+id/cardView" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="80dp"
        android:paddingBottom="10dp"
        android:text="LOGIN"
        android:textColor="@android:color/holo_blue_light"
        android:textSize="20sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.33"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/view" />


    <TextView
        android:id="@+id/moveREG"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="Register ?"
        android:textSize="20sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/view" />

</androidx.constraintlayout.widget.ConstraintLayout>
