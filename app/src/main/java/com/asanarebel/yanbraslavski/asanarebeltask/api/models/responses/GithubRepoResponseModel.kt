package com.asanarebel.yanbraslavski.asanarebeltask.api.models.responses

/**
 * Created by yan.braslavski on 11/14/17.
 * This model is auto generated from JSON string using a dedicated plugin.
 */
data class GithubRepoResponseModel(
        val id: Int, //3070104
        val name: String, //abs.io
        val full_name: String, //JakeWharton/abs.io
        val owner: Owner,
        val private: Boolean, //false
        val html_url: String, //https://github.com/JakeWharton/abs.io
        val description: String, //Simple URL shortener for ActionBarSherlock using node.js and express.
        val fork: Boolean, //false
        val url: String, //https://api.github.com/repos/JakeWharton/abs.io
        val forks_url: String, //https://api.github.com/repos/JakeWharton/abs.io/forks
        val keys_url: String, //https://api.github.com/repos/JakeWharton/abs.io/keys{/key_id}
        val collaborators_url: String, //https://api.github.com/repos/JakeWharton/abs.io/collaborators{/collaborator}
        val teams_url: String, //https://api.github.com/repos/JakeWharton/abs.io/teams
        val hooks_url: String, //https://api.github.com/repos/JakeWharton/abs.io/hooks
        val issue_events_url: String, //https://api.github.com/repos/JakeWharton/abs.io/issues/events{/number}
        val events_url: String, //https://api.github.com/repos/JakeWharton/abs.io/events
        val assignees_url: String, //https://api.github.com/repos/JakeWharton/abs.io/assignees{/user}
        val branches_url: String, //https://api.github.com/repos/JakeWharton/abs.io/branches{/branch}
        val tags_url: String, //https://api.github.com/repos/JakeWharton/abs.io/tags
        val blobs_url: String, //https://api.github.com/repos/JakeWharton/abs.io/git/blobs{/sha}
        val git_tags_url: String, //https://api.github.com/repos/JakeWharton/abs.io/git/tags{/sha}
        val git_refs_url: String, //https://api.github.com/repos/JakeWharton/abs.io/git/refs{/sha}
        val trees_url: String, //https://api.github.com/repos/JakeWharton/abs.io/git/trees{/sha}
        val statuses_url: String, //https://api.github.com/repos/JakeWharton/abs.io/statuses/{sha}
        val languages_url: String, //https://api.github.com/repos/JakeWharton/abs.io/languages
        val stargazers_url: String, //https://api.github.com/repos/JakeWharton/abs.io/stargazers
        val contributors_url: String, //https://api.github.com/repos/JakeWharton/abs.io/contributors
        val subscribers_url: String, //https://api.github.com/repos/JakeWharton/abs.io/subscribers
        val subscription_url: String, //https://api.github.com/repos/JakeWharton/abs.io/subscription
        val commits_url: String, //https://api.github.com/repos/JakeWharton/abs.io/commits{/sha}
        val git_commits_url: String, //https://api.github.com/repos/JakeWharton/abs.io/git/commits{/sha}
        val comments_url: String, //https://api.github.com/repos/JakeWharton/abs.io/comments{/number}
        val issue_comment_url: String, //https://api.github.com/repos/JakeWharton/abs.io/issues/comments{/number}
        val contents_url: String, //https://api.github.com/repos/JakeWharton/abs.io/contents/{+path}
        val compare_url: String, //https://api.github.com/repos/JakeWharton/abs.io/compare/{base}...{head}
        val merges_url: String, //https://api.github.com/repos/JakeWharton/abs.io/merges
        val archive_url: String, //https://api.github.com/repos/JakeWharton/abs.io/{archive_format}{/ref}
        val downloads_url: String, //https://api.github.com/repos/JakeWharton/abs.io/downloads
        val issues_url: String, //https://api.github.com/repos/JakeWharton/abs.io/issues{/number}
        val pulls_url: String, //https://api.github.com/repos/JakeWharton/abs.io/pulls{/number}
        val milestones_url: String, //https://api.github.com/repos/JakeWharton/abs.io/milestones{/number}
        val notifications_url: String, //https://api.github.com/repos/JakeWharton/abs.io/notifications{?since,all,participating}
        val labels_url: String, //https://api.github.com/repos/JakeWharton/abs.io/labels{/name}
        val releases_url: String, //https://api.github.com/repos/JakeWharton/abs.io/releases{/id}
        val deployments_url: String, //https://api.github.com/repos/JakeWharton/abs.io/deployments
        val created_at: String, //2011-12-29T18:02:34Z
        val updated_at: String, //2017-08-09T14:39:21Z
        val pushed_at: String, //2011-12-29T18:02:44Z
        val git_url: String, //git://github.com/JakeWharton/abs.io.git
        val ssh_url: String, //git@github.com:JakeWharton/abs.io.git
        val clone_url: String, //https://github.com/JakeWharton/abs.io.git
        val svn_url: String, //https://github.com/JakeWharton/abs.io
        val homepage: String, //http://abs.io
        val size: Int, //108
        val stargazers_count: Int, //6
        val watchers_count: Int, //6
        val language: String, //JavaScript
        val has_issues: Boolean, //true
        val has_projects: Boolean, //true
        val has_downloads: Boolean, //true
        val has_wiki: Boolean, //false
        val has_pages: Boolean, //false
        val forks_count: Int, //1
        val mirror_url: Any, //null
        val archived: Boolean, //false
        val open_issues_count: Int, //0
        val forks: Int, //1
        val open_issues: Int, //0
        val watchers: Int, //6
        val default_branch: String //master
)

data class Owner(
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