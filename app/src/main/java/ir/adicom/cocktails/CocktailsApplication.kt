package ir.adicom.cocktails

import android.app.Application
import android.content.Context
import ir.adicom.cocktails.common.network.CocktailsApi
import ir.adicom.cocktails.common.repository.CocktailsRepository
import ir.adicom.cocktails.common.repository.CocktailsRepositoryImpl
import ir.adicom.cocktails.game.factory.CocktailsGameFactory
import ir.adicom.cocktails.game.factory.CocktailsGameFactoryImpl

class CocktailsApplication : Application() {
    val repository: CocktailsRepository by lazy {
        CocktailsRepositoryImpl(
            CocktailsApi.create(),
            getSharedPreferences("Cocktails", Context.MODE_PRIVATE)
        )
    }

    val gameFactory: CocktailsGameFactory by lazy {
        CocktailsGameFactoryImpl(repository)
    }
}