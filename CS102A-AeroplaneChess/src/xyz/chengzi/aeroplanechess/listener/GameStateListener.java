package xyz.chengzi.aeroplanechess.listener;

import xyz.chengzi.aeroplanechess.controller.GameController;

public interface GameStateListener extends Listener {


    void onPlayerStartRound(int player,GameController controller) throws InterruptedException;

    void onPlayerEndRound(int player);
}
