package com.example.egyfwd2
import com.squareup.moshi.Json
import java.io.Serializable

data class AsteroidData(@Json(name = "near_earth_objects") val nearEarthObjects: Map<String, List<Asteroid>>)

data class Asteroid(
    val id: Long,
    val name: String,
    @Json(name = "close_approach_data") val closeApproachDate: List<ApproachList>,//date,velocity,distance"
    @Json(name = "absolute_magnitude_h") val absoluteMagnitude: Double,
    @Json(name = "estimated_diameter") val estimatedDiameter: Diameter,
    @Json(name = "is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean
): Serializable

data class ApproachList(
    @Json(name = "close_approach_date") val closeAprroachData:String,
    @Json(name = "relative_velocity") val speed:SpeedKPH,
    @Json(name = "miss_distance") val distanceFromEarth:MissDistancekilometers

)
data class Diameter(
    @Json(name = "kilometers") val diameterKG:DiameterKG
)
data class DiameterKG(
    @Json(name = "estimated_diameter_max") val DKG:Double
)
data class SpeedKPH(
    @Json(name = "kilometers_per_hour") val velocityKPH:Double
)
data class MissDistancekilometers(
    @Json(name = "kilometers") val mdk:Double
)