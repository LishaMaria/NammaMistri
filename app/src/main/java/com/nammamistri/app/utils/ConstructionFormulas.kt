package com.nammamistri.app.utils

import kotlin.math.ceil

data class MaterialResult(
    val bricks: Int,
    val cementBags: Double,
    val sandLoads: Double,
    val wallVolume: Double
)

object ConstructionFormulas {
    // Standard brick size in meters: 0.19 x 0.09 x 0.09 + mortar joints
    private const val BRICK_LENGTH = 0.20
    private const val BRICK_HEIGHT = 0.10
    private const val MORTAR_RATIO = 1.3 // 30% wastage factor

    fun calculateMaterials(
        lengthFt: Double,
        heightFt: Double,
        thicknessInches: Double
    ): MaterialResult {
        // Convert to meters
        val length = lengthFt * 0.3048
        val height = heightFt * 0.3048
        val thickness = thicknessInches * 0.0254

        val wallVolume = length * height * thickness

        // Bricks per cubic meter = 1 / (brick volume with mortar)
        val bricksPerCubicMeter = 1.0 / (BRICK_LENGTH * BRICK_HEIGHT * thickness.coerceAtLeast(0.10))
        val brickCount = ceil(wallVolume * bricksPerCubicMeter * MORTAR_RATIO).toInt()

        // Cement: ~1.5 bags per cubic meter of wall (1:6 mortar mix)
        val cementBags = wallVolume * 1.5

        // Sand: ~0.9 cubic meters per cubic meter of wall
        val sandLoads = wallVolume * 0.9

        return MaterialResult(
            bricks = brickCount.coerceAtLeast(1),
            cementBags = String.format("%.1f", cementBags).toDouble(),
            sandLoads = String.format("%.2f", sandLoads).toDouble(),
            wallVolume = String.format("%.2f", wallVolume).toDouble()
        )
    }
}