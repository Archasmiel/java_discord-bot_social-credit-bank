package net.archasmiel.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BotCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("status")){
            event.reply("Working").queue();
        }
        if (event.getName().equals("members")) {
            StringBuilder members = new StringBuilder();
            for (Member member: event.getGuild().getMembers())
                members.append(member.getId()).append(" ").append(member.getEffectiveName()).append("\n");
            event.reply(members.toString()).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        Guild guild = event.getGuild();
        List<CommandData> commands = new ArrayList<>();
        commands.add(Commands.slash("status", "Get my status"));
        commands.add(Commands.slash("members", "Get server member list"));
        guild.updateCommands().addCommands(commands).queue();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        for (Guild g: event.getJDA().getGuilds())
            System.out.println(g.getName());
    }
}
