package fr.sny1411.bingo.commands;

import fr.sny1411.bingo.Bingo;
import fr.sny1411.bingo.Game;
import fr.sny1411.bingo.utils.Countdown;
import fr.sny1411.bingo.utils.Text;
import fr.sny1411.bingo.utils.Timer;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EndGame implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            Game game = Bingo.getGame();
            if (game.getEtat() == Game.Etat.STARTING) {
                Countdown.cancel();
                game.setEtat(Game.Etat.SETUP);
                Text.broadcastMessage("§7[§eBINGO§7] §fLancement annulé");
                return false;
            }
            if (game.getEtat() != Game.Etat.INGAME) {
                sender.sendMessage(Component.text("§8[§c⚠§8] §fAucune partie en cours"));
                return false;
            }
            Timer.stop();
        }
        return false;
    }
}
