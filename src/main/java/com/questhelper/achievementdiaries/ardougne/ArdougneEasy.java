package com.questhelper.achievementdiaries.ardougne;

import com.questhelper.*;
import com.questhelper.banktab.BankSlotIcons;
import com.questhelper.questhelpers.ComplexStateQuestHelper;
import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.Requirement;
import com.questhelper.requirements.ZoneRequirement;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.quest.QuestRequirement;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.requirements.var.VarplayerRequirement;
import com.questhelper.rewards.ItemReward;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.*;

import java.util.*;

import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.panel.PanelDetails;
import net.runelite.client.ui.overlay.components.PanelComponent;

import java.awt.*;

@QuestDescriptor(
        quest = QuestHelperQuest.ARDOUGNE_EASY
)
public class ArdougneEasy extends ComplexStateQuestHelper {

    //Items Required
    ItemRequirement coins100, silk, rustySword;

    //Items Recommended
    ItemRequirement knifeOrSlashing;

    QuestRequirement runeMysteries, biohazard;

    Requirement notTeleportedRuneEssence, notStolenCake, notSoldSilk, notPrayAltar, notFishingTrawler, notCombatCamp, notTindalMarchant,
    notLeverWilderness, notHunterShop, notCheckPet;

    QuestStep claimReward, teleportRuneEssence, stealCake, sellSilk, prayAltar, fishingTrawler,
    pullLeverWilderness, checkHunterShop, checkPet;

    @Override
    public QuestStep loadStep() {
        setupRequirements();
        setupSteps();
        ConditionalStep doEasy = new ConditionalStep(this, claimReward);
       doEasy.addStep(notTeleportedRuneEssence, teleportRuneEssence);
        doEasy.addStep(notStolenCake, stealCake);
        doEasy.addStep(notSoldSilk, sellSilk);
        doEasy.addStep(notPrayAltar, prayAltar);
        doEasy.addStep(notFishingTrawler, fishingTrawler);
        doEasy.addStep(notLeverWilderness, pullLeverWilderness);
        doEasy.addStep(notHunterShop, checkHunterShop);
        doEasy.addStep(notCheckPet, checkPet);

       return doEasy;
    }

    private void setupRequirements()
    {
        notTeleportedRuneEssence = new VarplayerRequirement(1186, false, 0);
        notStolenCake = new VarplayerRequirement(1186, false, 1);
        notSoldSilk = new VarplayerRequirement(1186, false, 2);
        notPrayAltar = new VarplayerRequirement(1186, false, 3);
        notFishingTrawler = new VarplayerRequirement(1186, false, 4);
        notCombatCamp = new VarplayerRequirement(1186, false, 5);
        notTindalMarchant = new VarplayerRequirement(1186, false, 6);
        notLeverWilderness = new VarplayerRequirement(1186, false, 7);
        notHunterShop = new VarplayerRequirement(1186, false, 8);
        notCheckPet = new VarplayerRequirement(1186, false, 9);


        //Required items
        coins100 = new ItemRequirement("Coins", ItemID.COINS_995, 100);
        silk = new ItemRequirement("Silk", ItemID.SILK, 1);
        rustySword = new ItemRequirement("Rusty Sword", ItemID.RUSTY_SWORD, 1);

        //Required quests
        runeMysteries = new QuestRequirement(QuestHelperQuest.RUNE_MYSTERIES, QuestState.FINISHED);
        biohazard = new QuestRequirement(QuestHelperQuest.BIOHAZARD, QuestState.FINISHED);
    }

    private void setupSteps()
    {
        teleportRuneEssence = new NpcStep(this, NpcID.WIZARD_CROMPERTY,
                new WorldPoint(0, 0, 0), "Have Wizard Cromperty teleport you to the Rune essence mine.", runeMysteries);
        stealCake = new ObjectStep(this, ObjectID.BAKERS_STALL, new WorldPoint(0, 0, 0),
                "")

        //Claim Reward
        claimReward = new NpcStep(this, NpcID.SIR_REBRAL, new WorldPoint(2977, 3346, 0),
                "Congratulations! Talk to Sir Rebral in the courtyard of The White Knight Castle to claim your reward!");
        claimReward.addDialogStep("I have a question about my Achievement Diary.");
    }



    @Override
    public void renderDebugWorldOverlayHint(Graphics2D graphics, QuestHelperPlugin plugin, QuestHelper quest, PanelComponent panelComponent) {

    }

    @Override
    public void renderDebugWidgetOverlayHint(Graphics2D graphics, QuestHelperPlugin plugin, QuestHelper quest, PanelComponent panelComponent) {

    }
}
