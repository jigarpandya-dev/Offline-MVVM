package com.mvvmstructure.offline.ui.common.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(

	@field:JsonProperty("results")
	val results: List<User?>? = null,

	@field:JsonProperty("info")
	val info: Info? = null
)

data class Coordinates(

	@field:JsonProperty("latitude")
	val latitude: String? = null,

	@field:JsonProperty("longitude")
	val longitude: String? = null
)

data class Street(

	@field:JsonProperty("number")
	val number: Int? = null,

	@field:JsonProperty("name")
	val name: String? = null
)

data class Id(

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("value")
	val value: String? = null
)

data class Login(

	@field:JsonProperty("sha1")
	val sha1: String? = null,

	@field:JsonProperty("password")
	val password: String? = null,

	@field:JsonProperty("salt")
	val salt: String? = null,

	@field:JsonProperty("sha256")
	val sha256: String? = null,

	@field:JsonProperty("uuid")
	val uuid: String? = null,

	@field:JsonProperty("username")
	val username: String? = null,

	@field:JsonProperty("md5")
	val md5: String? = null
)

data class Dob(

	@field:JsonProperty("date")
	val date: String? = null,

	@field:JsonProperty("age")
	val age: Int? = null
)

data class Location(

	@field:JsonProperty("country")
	val country: String? = null,

	@field:JsonProperty("city")
	val city: String? = null,

	@Embedded(prefix = "street_")
	@field:JsonProperty("street")
	val street: Street? = null,

	@Embedded
	@field:JsonProperty("timezone")
	val timezone: Timezone? = null,

	@field:JsonProperty("postcode")
	val postcode: String? = null,

	@Embedded
	@field:JsonProperty("coordinates")
	val coordinates: Coordinates? = null,

	@field:JsonProperty("state")
	val state: String? = null
)

data class Picture(

	@field:JsonProperty("thumbnail")
	val thumbnail: String? = null,

	@field:JsonProperty("large")
	val large: String? = null,

	@field:JsonProperty("medium")
	val medium: String? = null
)

data class Info(

	@field:JsonProperty("seed")
	val seed: String? = null,

	@field:JsonProperty("page")
	val page: Int? = null,

	@field:JsonProperty("results")
	val results: Int? = null,

	@field:JsonProperty("version")
	val version: String? = null
)

@Entity
data class User(

	@PrimaryKey(autoGenerate = true)
	val userId:Int=0,

	@field:JsonProperty("nat")
	val nat: String? = null,

	@field:JsonProperty("gender")
	val gender: String? = null,

	@field:JsonProperty("phone")
	val phone: String? = null,

	@Embedded(prefix = "dob_")
	@field:JsonProperty("dob")
	val dob: Dob? = null,

	@Embedded
	@field:JsonProperty("name")
	val name: Name? = null,

	@Embedded
	@field:JsonProperty("registered")
	val registered: Registered? = null,

	@Embedded
	@field:JsonProperty("location")
	val location: Location? = null,

	@Embedded
	@field:JsonProperty("id")
	val id: Id? = null,

	@Embedded
	@field:JsonProperty("login")
	val login: Login? = null,

	@field:JsonProperty("cell")
	val cell: String? = null,

	@field:JsonProperty("email")
	val email: String? = null,

	@Embedded
	@field:JsonProperty("picture")
	val picture: Picture? = null
)

data class Registered(

	@field:JsonProperty("date")
	val date: String? = null,

	@field:JsonProperty("age")
	val age: Int? = null
)

data class Timezone(

	@field:JsonProperty("offset")
	val offset: String? = null,

	@field:JsonProperty("description")
	val description: String? = null
)

data class Name(

	@field:JsonProperty("last")
	val last: String? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("first")
	val first: String? = null
)
