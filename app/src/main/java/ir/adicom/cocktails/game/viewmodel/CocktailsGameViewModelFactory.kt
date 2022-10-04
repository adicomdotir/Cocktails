package ir.adicom.cocktails.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.adicom.cocktails.common.repository.CocktailsRepository
import ir.adicom.cocktails.game.factory.CocktailsGameFactory

class CocktailsGameViewModelFactory(
    private val repository: CocktailsRepository,
    private val factory: CocktailsGameFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CocktailsGameViewModel(repository, factory) as T
    }
}
