package com.app.imagerandom.domain.model

data class ImageRandom(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blurHash: String,
    val likes: Int,
    val likedByUser: Boolean,
    val description: String?,
    val user: User,
    val currentUserCollections: List<Collection>,
    val urls: Urls,
    val links: Links
)

data class User(
    val id: String,
    val username: String,
    val name: String,
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val totalLikes: Int,
    val totalPhotos: Int,
    val totalCollections: Int,
    val instagramUsername: String?,
    val twitterUsername: String?,
    val profileImage: ProfileImage,
    val links: UserLinks
)

data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

data class UserLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String
)

data class Collection(
    val id: Int,
    val title: String,
    val publishedAt: String,
    val lastCollectedAt: String,
    val updatedAt: String,
    val coverPhoto: Any?, // Can be more specific if the structure is known
    val user: Any? // Can be more specific if the structure is known
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class Links(
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
)