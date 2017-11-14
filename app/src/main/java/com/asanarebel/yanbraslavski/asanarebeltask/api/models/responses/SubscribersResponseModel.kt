package com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses

/**
 * Created by yan.braslavski on 11/14/17.
 */
data class SubscribersResponseModel(
		val login: String, //JakeWharton
		val id: Int, //66577
		val avatar_url: String, //https://avatars0.githubusercontent.com/u/66577?v=4
		val gravatar_id: String,
		val url: String, //https://api.github.com/users/JakeWharton
		val html_url: String, //https://github.com/JakeWharton
		val followers_url: String, //https://api.github.com/users/JakeWharton/followers
		val following_url: String, //https://api.github.com/users/JakeWharton/following{/other_user}
		val gists_url: String, //https://api.github.com/users/JakeWharton/gists{/gist_id}
		val starred_url: String, //https://api.github.com/users/JakeWharton/starred{/owner}{/repo}
		val subscriptions_url: String, //https://api.github.com/users/JakeWharton/subscriptions
		val organizations_url: String, //https://api.github.com/users/JakeWharton/orgs
		val repos_url: String, //https://api.github.com/users/JakeWharton/repos
		val events_url: String, //https://api.github.com/users/JakeWharton/events{/privacy}
		val received_events_url: String, //https://api.github.com/users/JakeWharton/received_events
		val type: String, //User
		val site_admin: Boolean //false
)