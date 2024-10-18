package com.example.gameapp.data.mappers

import androidx.lifecycle.MutableLiveData
import com.example.gameapp.data.database.entities.EntityGame
import com.example.gameapp.models.Game

class GameMapper {
    // Function when convert the one entity to Object.
    fun mapEntityToGame(entity: EntityGame): Game {
        return Game(
            id = entity.id,
            title = entity.title ?: "",
            thumbnail = entity.thumbnail ?: "",
            short_description = entity.short_description ?: "",
            game_url = entity.game_url ?: "",
            genre = entity.genre ?: "",
            platform = entity.platform ?: "",
            publisher = entity.publisher ?: "",
            developer = entity.developer ?: "",
            release_date = entity.release_date ?: "",
            freetogame_profile_url = entity.freetogame_profile_url ?: ""
        )
    }

    // Function when convert the one object to Entity.
    fun mapGameToEntity(game: Game): EntityGame {
        return EntityGame(
            id = game.id,
            title = game.title,
            thumbnail = game.thumbnail,
            short_description = game.short_description,
            game_url = game.game_url,
            genre = game.genre,
            platform = game.platform,
            publisher = game.publisher,
            developer = game.developer,
            release_date = game.release_date,
            freetogame_profile_url = game.freetogame_profile_url
        )
    }

    // Function when convert the list of objects to list Entities.
    fun mapGamesToEntities(gamesLiveData: MutableLiveData<List<Game>>): List<EntityGame> {
        val games = gamesLiveData.value ?: emptyList()
        return games.map { game ->
            EntityGame(
                id = game.id,
                title = game.title,
                thumbnail = game.thumbnail,
                short_description = game.short_description,
                game_url = game.game_url,
                genre = game.genre,
                platform = game.platform,
                publisher = game.publisher,
                developer = game.developer,
                release_date = game.release_date,
                freetogame_profile_url = game.freetogame_profile_url
            )
        }
    }

    fun mapEntitiesToGames(entities: List<EntityGame>): List<Game> {
        return entities.map { entity ->
            Game(
                id = entity.id,
                title = entity.title ?: "",
                thumbnail = entity.thumbnail ?: "",
                short_description = entity.short_description ?: "",
                game_url = entity.game_url ?: "",
                genre = entity.genre ?: "",
                platform = entity.platform ?: "",
                publisher = entity.publisher ?: "",
                developer = entity.developer ?: "",
                release_date = entity.release_date ?: "",
                freetogame_profile_url = entity.freetogame_profile_url ?: ""
            )
        }
    }
}