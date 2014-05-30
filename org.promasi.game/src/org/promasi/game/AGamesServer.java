/**
 *
 */
package org.promasi.game;

import java.util.List;

import org.promasi.utilities.design.Observer;

/**
 * @author alekstheod Represent the GamesServer on ProMaSi system.
 */
public abstract class AGamesServer extends Observer<IGamesServerListener> {

    /**
     * User will call this method in order to update a available games list.
     *
     */
    public abstract void requestGamesList();

    /**
     * User can call this method in order to join to the given game.
     *
     * @param game Instance of {@link IGame} interface implementation which
     * represent the game.
     */
    public abstract void joinGame(IGame game);

    /**
     * @brief request if a new game creation is allowed.
     * @return true if server allows to create a new game, false otherwise.
     */
    public abstract boolean isNewGameAllowed();

    /**
     * An child class will call this method in order to update the games list.
     *
     * @param games
     */
    protected void updateGamesList(List<IGame> games) {
        for (IGamesServerListener listener : getListeners()) {
            listener.updateGamesList(games);
        }
    }

    /**
     * An child class will call this method in order to inform the games server
     * that user would like to join to the given game.
     *
     * @param game
     */
    protected void onJoinGame(IGame game) {
        for (IGamesServerListener listener : getListeners()) {
            listener.onJoinGame(game);
        }
    }
}
