package ir.adicom.cocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.adicom.cocktails.databinding.ActivityCocktailsGameBinding

class CocktailsGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCocktailsGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityCocktailsGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}