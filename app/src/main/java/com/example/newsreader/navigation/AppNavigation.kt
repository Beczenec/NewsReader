package com.example.newsreader.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsreader.ui.ArticleDetailScreen
import com.example.newsreader.ui.NewsListScreen
import com.example.newsreader.ui.NewsViewModel
import com.example.newsreader.ui.NewsViewModelFactory

object Routes {
    const val NEWS = "news"
    const val DETAIL =
        "detail/{title}/{source}/{author}/{date}/{description}/{imageUrl}/{articleUrl}"

    fun detail(
        title: String, source: String, author: String, date: String,
        description: String, imageUrl: String, articleUrl: String
    ) = "detail/${Uri.encode(title)}/${Uri.encode(source)}/${Uri.encode(author)}/" +
            "${Uri.encode(date)}/${Uri.encode(description)}/${Uri.encode(imageUrl)}/${Uri.encode(articleUrl)}"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: NewsViewModel = viewModel(factory = NewsViewModelFactory())

    NavHost(navController = navController, startDestination = Routes.NEWS) {
        composable(Routes.NEWS) {
            NewsListScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    navController.navigate(
                        Routes.detail(
                            article.title.orEmpty(),
                            article.source?.name.orEmpty(),
                            article.author.orEmpty(),
                            article.publishedAt.orEmpty(),
                            article.description.orEmpty(),
                            article.urlToImage.orEmpty(),
                            article.url.orEmpty()
                        )
                    )
                }
            )
        }

        composable(
            Routes.DETAIL,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("source") { type = NavType.StringType },
                navArgument("author") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("imageUrl") { type = NavType.StringType },
                navArgument("articleUrl") { type = NavType.StringType }
            )
        ) { entry ->
            fun arg(name: String) = entry.arguments?.getString(name).orEmpty()

            ArticleDetailScreen(
                title = arg("title"),
                source = arg("source"),
                author = arg("author"),
                date = arg("date"),
                description = arg("description"),
                imageUrl = arg("imageUrl"),
                articleUrl = arg("articleUrl"),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
