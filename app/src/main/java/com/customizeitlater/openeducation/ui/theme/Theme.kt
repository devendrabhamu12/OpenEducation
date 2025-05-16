package com.customizeitlater.openeducation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


// Light theme color scheme
val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_primary
)

// Dark theme color scheme
val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_errorContainer,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_primary
)


val LightColorSchemeMediumContrast = lightColorScheme(
    primary = md_theme_light_medium_primary,
    onPrimary = md_theme_light_medium_onPrimary,
    primaryContainer = md_theme_light_medium_primaryContainer,
    onPrimaryContainer = md_theme_light_medium_onPrimaryContainer,

    secondary = md_theme_light_medium_secondary,
    onSecondary = md_theme_light_medium_onSecondary,
    secondaryContainer = md_theme_light_medium_secondaryContainer,
    onSecondaryContainer = md_theme_light_medium_onSecondaryContainer,

    tertiary = md_theme_light_medium_tertiary,
    onTertiary = md_theme_light_medium_onTertiary,
    tertiaryContainer = md_theme_light_medium_tertiaryContainer,
    onTertiaryContainer = md_theme_light_medium_onTertiaryContainer,

    error = md_theme_light_medium_error,
    onError = md_theme_light_medium_onError,
    errorContainer = md_theme_light_medium_errorContainer,
    onErrorContainer = md_theme_light_medium_onErrorContainer,

    background = md_theme_light_medium_background,
    onBackground = md_theme_light_medium_onBackground,
    surface = md_theme_light_medium_surface,
    onSurface = md_theme_light_medium_onSurface,
    surfaceVariant = md_theme_light_medium_surfaceVariant,
    onSurfaceVariant = md_theme_light_medium_onSurfaceVariant,

    outline = md_theme_light_medium_outline,
    inverseOnSurface = md_theme_light_medium_inverseOnSurface,
    inverseSurface = md_theme_light_medium_inverseSurface,
    inversePrimary = md_theme_light_medium_inversePrimary,
    surfaceTint = md_theme_light_medium_primary,
    outlineVariant = md_theme_light_medium_outlineVariant,
    scrim = md_theme_light_medium_scrim
)

val DarkColorSchemeMediumContrast = darkColorScheme(
    primary = md_theme_dark_medium_primary,
    onPrimary = md_theme_dark_medium_onPrimary,
    primaryContainer = md_theme_dark_medium_primaryContainer,
    onPrimaryContainer = md_theme_dark_medium_onPrimaryContainer,

    secondary = md_theme_dark_medium_secondary,
    onSecondary = md_theme_dark_medium_onSecondary,
    secondaryContainer = md_theme_dark_medium_secondaryContainer,
    onSecondaryContainer = md_theme_dark_medium_onSecondaryContainer,

    tertiary = md_theme_dark_medium_tertiary,
    onTertiary = md_theme_dark_medium_onTertiary,
    tertiaryContainer = md_theme_dark_medium_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_medium_onTertiaryContainer,

    error = md_theme_dark_medium_error,
    onError = md_theme_dark_medium_onError,
    errorContainer = md_theme_dark_medium_errorContainer,
    onErrorContainer = md_theme_dark_medium_onErrorContainer,

    background = md_theme_dark_medium_background,
    onBackground = md_theme_dark_medium_onBackground,
    surface = md_theme_dark_medium_surface,
    onSurface = md_theme_dark_medium_onSurface,
    surfaceVariant = md_theme_dark_medium_surfaceVariant,
    onSurfaceVariant = md_theme_dark_medium_onSurfaceVariant,

    outline = md_theme_dark_medium_outline,
    inverseOnSurface = md_theme_dark_medium_inverseOnSurface,
    inverseSurface = md_theme_dark_medium_inverseSurface,
    inversePrimary = md_theme_dark_medium_inversePrimary,
    surfaceTint = md_theme_dark_medium_primary,
    outlineVariant = md_theme_dark_medium_outlineVariant,
    scrim = md_theme_dark_medium_scrim
)

val LightColorSchemeHighContrast = lightColorScheme(
    primary = md_theme_light_high_primary,
    onPrimary = md_theme_light_high_onPrimary,
    primaryContainer = md_theme_light_high_primaryContainer,
    onPrimaryContainer = md_theme_light_high_onPrimaryContainer,

    secondary = md_theme_light_high_secondary,
    onSecondary = md_theme_light_high_onSecondary,
    secondaryContainer = md_theme_light_high_secondaryContainer,
    onSecondaryContainer = md_theme_light_high_onSecondaryContainer,

    tertiary = md_theme_light_high_tertiary,
    onTertiary = md_theme_light_high_onTertiary,
    tertiaryContainer = md_theme_light_high_tertiaryContainer,
    onTertiaryContainer = md_theme_light_high_onTertiaryContainer,

    error = md_theme_light_high_error,
    onError = md_theme_light_high_onError,
    errorContainer = md_theme_light_high_errorContainer,
    onErrorContainer = md_theme_light_high_onErrorContainer,

    background = md_theme_light_high_background,
    onBackground = md_theme_light_high_onBackground,
    surface = md_theme_light_high_surface,
    onSurface = md_theme_light_high_onSurface,
    surfaceVariant = md_theme_light_high_surfaceVariant,
    onSurfaceVariant = md_theme_light_high_onSurfaceVariant,

    outline = md_theme_light_high_outline,
    inverseOnSurface = md_theme_light_high_inverseOnSurface,
    inverseSurface = md_theme_light_high_inverseSurface,
    inversePrimary = md_theme_light_high_inversePrimary,
    surfaceTint = md_theme_light_high_primary,
    outlineVariant = md_theme_light_high_outlineVariant,
    scrim = md_theme_light_high_scrim
)

val DarkColorSchemeHighContrast = darkColorScheme(
    primary = md_theme_dark_high_primary,
    onPrimary = md_theme_dark_high_onPrimary,
    primaryContainer = md_theme_dark_high_primaryContainer,
    onPrimaryContainer = md_theme_dark_high_onPrimaryContainer,

    secondary = md_theme_dark_high_secondary,
    onSecondary = md_theme_dark_high_onSecondary,
    secondaryContainer = md_theme_dark_high_secondaryContainer,
    onSecondaryContainer = md_theme_dark_high_onSecondaryContainer,

    tertiary = md_theme_dark_high_tertiary,
    onTertiary = md_theme_dark_high_onTertiary,
    tertiaryContainer = md_theme_dark_high_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_high_onTertiaryContainer,

    error = md_theme_dark_high_error,
    onError = md_theme_dark_high_onError,
    errorContainer = md_theme_dark_high_errorContainer,
    onErrorContainer = md_theme_dark_high_onErrorContainer,

    background = md_theme_dark_high_background,
    onBackground = md_theme_dark_high_onBackground,
    surface = md_theme_dark_high_surface,
    onSurface = md_theme_dark_high_onSurface,
    surfaceVariant = md_theme_dark_high_surfaceVariant,
    onSurfaceVariant = md_theme_dark_high_onSurfaceVariant,

    outline = md_theme_dark_high_outline,
    inverseOnSurface = md_theme_dark_high_inverseOnSurface,
    inverseSurface = md_theme_dark_high_inverseSurface,
    inversePrimary = md_theme_dark_high_inversePrimary,
    surfaceTint = md_theme_dark_high_primary,
    outlineVariant = md_theme_dark_high_outlineVariant,
    scrim = md_theme_dark_high_scrim
)


@Composable
fun OpenEducationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme ->DarkColorSchemeHighContrast
        else ->LightColorSchemeHighContrast
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}