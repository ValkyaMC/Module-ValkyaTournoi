package fr.volax.valkyatournoi.tournoi;

import fr.volax.valkyatournoi.ValkyaTournoi;
import fr.volax.valkyatournoi.util.MessageUtil;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Tournoi {
    public int task, timer;
    public TournoiGame tournoiGame;

    public void startTournoi(){
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(ValkyaTournoi.getInstance(), new Runnable() {
            @Override
            public void run() {
                timer++;
                MessageUtil.debugMesssage("Ca fait " + timer + " secondes que le tournoi est start !");
                MessageUtil.debugMesssage("Il y a actuellement " + ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() + " joueurs dans le tournoi !");
                MessageUtil.debugMesssage("Current State " + TournoiState.getState());
                int playersSize = ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size();
                int waitingPlayer = ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().size();
                if(TournoiState.isState(TournoiState.INVITATION)){
                    switch (timer){
                        case 2700:
                            MessageUtil.debugMesssage("15 minutes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §615 §eminutes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3240:
                            MessageUtil.debugMesssage("10 minutes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §610 §eminutes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3420:
                            MessageUtil.debugMesssage("5 minutes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §65 §eminutes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3540:
                            MessageUtil.debugMesssage("1 minute avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §61 §eminute pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3570:
                            MessageUtil.debugMesssage("30 secondes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §630 §esecondes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3585:
                            MessageUtil.debugMesssage("15 secondes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §615 §esecondes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3590:
                            MessageUtil.debugMesssage("10 secondes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §610 §esecondes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 3595:
                            MessageUtil.debugMesssage("5 secondes avant le début du tournoi !");
                            MessageUtil.broadcastMessage("§eIl ne reste plus que §65 §esecondes pour s'inscire au tournoi ! (/tournoi join)");
                            break;
                        case 30:
                            MessageUtil.debugMesssage("Début du tournoi");
                            MessageUtil.broadcastMessage("§eDébut du tournoi ! Bonne chance à vous !");

                            if (playersSize < 128 && playersSize >=64){
                                TournoiState.setState(TournoiState.POULE);
                                MessageUtil.broadcastMessage("§eDébut des phases de Poules !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if (playersSize <= 64 && playersSize > 32){
                                TournoiState.setState(TournoiState.TRENTE_DEUX_EME);
                                MessageUtil.broadcastMessage("§eDébut des 32ème !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if (playersSize <= 32 && playersSize > 16){
                                TournoiState.setState(TournoiState.SEIZE_EME);
                                MessageUtil.broadcastMessage("§eDébut des 16ème !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if (playersSize <= 16 && playersSize > 8){
                                TournoiState.setState(TournoiState.HUIT_EME);
                                MessageUtil.broadcastMessage("§eDébut des 8ème !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if (playersSize <= 8 && playersSize > 4){
                                TournoiState.setState(TournoiState.QUART);
                                MessageUtil.broadcastMessage("§eDébut des Quart !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if (playersSize <= 4 && playersSize > 2){
                                TournoiState.setState(TournoiState.DEMI);
                                MessageUtil.broadcastMessage("§eDébut des Demi !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if (playersSize == 2){
                                TournoiState.setState(TournoiState.FINAL);
                                MessageUtil.broadcastMessage("§eDébut de la Final !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                break;
                            }else if(playersSize == 1){
                                MessageUtil.broadcastMessage("§eImpossible de lancer le tournoi ! Pas assez de joueur !");
                                stopTournoi();
                                break;
                            }
                            break;
                        default:break;
                    }
                }
                if(TournoiState.isState(TournoiState.POULE)){
                    if (playersSize < 128 && playersSize >=64){
                        TournoiState.setState(TournoiState.POULE);
                        if(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() < 2) return;
                        if(tournoiGame == null){
                            if (waitingPlayer == 0 || waitingPlayer == 1){
                                TournoiState.setState(TournoiState.TRENTE_DEUX_EME);
                                MessageUtil.broadcastMessage("§eDébut des 32ème !");
                                ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().forEach(tournoiPlayer -> tournoiPlayer.setWaiting(true));
                                return;
                            }
                            fight();
                        }
                        return;
                    }else if (playersSize <= 64 && playersSize > 32){
                        TournoiState.setState(TournoiState.TRENTE_DEUX_EME);
                        MessageUtil.broadcastMessage("§eDébut des 32ème !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                        return;
                    }else if (playersSize <= 32 && playersSize > 16){
                        TournoiState.setState(TournoiState.SEIZE_EME);
                        MessageUtil.broadcastMessage("§eDébut des 16ème !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 16 && playersSize > 8){
                        TournoiState.setState(TournoiState.HUIT_EME);
                        MessageUtil.broadcastMessage("§eDébut des 8ème !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 8 && playersSize > 4){
                        TournoiState.setState(TournoiState.QUART);
                        MessageUtil.broadcastMessage("§eDébut des Quart !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 4 && playersSize > 2){
                        TournoiState.setState(TournoiState.DEMI);
                        MessageUtil.broadcastMessage("§eDébut des Demi !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize == 2){
                        TournoiState.setState(TournoiState.FINAL);
                        MessageUtil.broadcastMessage("§eDébut de la Final !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if(playersSize == 1) {
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }

                if(TournoiState.isState(TournoiState.TRENTE_DEUX_EME)){
                    if (playersSize <= 64 && playersSize > 32){
                        TournoiState.setState(TournoiState.TRENTE_DEUX_EME);

                        if(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() < 2) return;
                        if(tournoiGame == null){
                            if (waitingPlayer == 0 || waitingPlayer == 1){
                                TournoiState.setState(TournoiState.SEIZE_EME);
                                MessageUtil.broadcastMessage("§eDébut des 16ème !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                return;
                            }
                            fight();
                        }
                    }else if (playersSize <= 32 && playersSize > 16){
                        TournoiState.setState(TournoiState.SEIZE_EME);
                        MessageUtil.broadcastMessage("§eDébut des 16ème !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 16 && playersSize > 8){
                        TournoiState.setState(TournoiState.HUIT_EME);
                        MessageUtil.broadcastMessage("§eDébut des 8ème !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 8 && playersSize > 4){
                        TournoiState.setState(TournoiState.QUART);
                        MessageUtil.broadcastMessage("§eDébut des Quart !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 4 && playersSize > 2){
                        TournoiState.setState(TournoiState.DEMI);
                        MessageUtil.broadcastMessage("§eDébut des Demi !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize == 2){
                        TournoiState.setState(TournoiState.FINAL);
                        MessageUtil.broadcastMessage("§eDébut de la Final !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if(playersSize == 1){
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }

                if(TournoiState.isState(TournoiState.SEIZE_EME)){
                    if (playersSize <= 32 && playersSize > 16){
                        TournoiState.setState(TournoiState.SEIZE_EME);

                        if(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() < 2) return;
                        if(tournoiGame == null){
                            if (waitingPlayer == 0 || waitingPlayer == 1){
                                TournoiState.setState(TournoiState.HUIT_EME);
                                MessageUtil.broadcastMessage("§eDébut des 8ème !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                return;
                            }
                            fight();
                        }
                    }else if (playersSize <= 16 && playersSize > 8){
                        TournoiState.setState(TournoiState.HUIT_EME);
                        MessageUtil.broadcastMessage("§eDébut des 8ème !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 8 && playersSize > 4){
                        TournoiState.setState(TournoiState.QUART);
                        MessageUtil.broadcastMessage("§eDébut des Quart !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 4 && playersSize > 2){
                        TournoiState.setState(TournoiState.DEMI);
                        MessageUtil.broadcastMessage("§eDébut des Demi !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize == 2){
                        TournoiState.setState(TournoiState.FINAL);
                        MessageUtil.broadcastMessage("§eDébut de la Final !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if(playersSize == 1){
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }

                if(TournoiState.isState(TournoiState.HUIT_EME)){
                    if (playersSize <= 16 && playersSize > 8){
                        TournoiState.setState(TournoiState.HUIT_EME);

                        if(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() < 2) return;
                        if(tournoiGame == null){
                            if (waitingPlayer == 0 || waitingPlayer == 1){
                                TournoiState.setState(TournoiState.QUART);
                                MessageUtil.broadcastMessage("§eDébut des Quart !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                return;
                            }
                            fight();
                        }
                    }else if (playersSize <= 8 && playersSize > 4){
                        TournoiState.setState(TournoiState.QUART);
                        MessageUtil.broadcastMessage("§eDébut des Quart !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize <= 4 && playersSize > 2){
                        TournoiState.setState(TournoiState.DEMI);
                        MessageUtil.broadcastMessage("§eDébut des Demi !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize == 2){
                        TournoiState.setState(TournoiState.FINAL);
                        MessageUtil.broadcastMessage("§eDébut de la Final !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if(playersSize == 1){
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }

                if(TournoiState.isState(TournoiState.QUART)){
                    if (playersSize <= 8 && playersSize > 4){
                        TournoiState.setState(TournoiState.QUART);

                        if(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() < 2) return;
                        if(tournoiGame == null){
                            if (waitingPlayer == 0 || waitingPlayer == 1){
                                TournoiState.setState(TournoiState.DEMI);
                                MessageUtil.broadcastMessage("§eDébut des Demi !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                return;
                            }
                            fight();
                        }
                    }else if (playersSize <= 4 && playersSize > 2){
                        TournoiState.setState(TournoiState.DEMI);
                        MessageUtil.broadcastMessage("§eDébut des Demi !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if (playersSize == 2){
                        TournoiState.setState(TournoiState.FINAL);
                        MessageUtil.broadcastMessage("§eDébut de la Final !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if(playersSize == 1){
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }

                if(TournoiState.isState(TournoiState.DEMI)){
                    if (playersSize <= 4 && playersSize > 2){
                        TournoiState.setState(TournoiState.DEMI);

                        if(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().size() < 2) return;
                        if(tournoiGame == null){
                            if (waitingPlayer == 0 || waitingPlayer == 1){
                                TournoiState.setState(TournoiState.FINAL);
                                MessageUtil.broadcastMessage("§eDébut de la Final !");
                                for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                                    System.out.println(tournoiPlayer.getPlayer().getName());
                                    tournoiPlayer.setWaiting(true);
                                    tournoiPlayer.setOnFight(false);
                                    tournoiPlayer.setDead(false);
                                }
                                return;
                            }
                            fight();
                        }
                    }else if (playersSize == 2){
                        TournoiState.setState(TournoiState.FINAL);
                        MessageUtil.broadcastMessage("§eDébut de la Final !");
                        for(TournoiPlayer tournoiPlayer : ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList()){
                            System.out.println(tournoiPlayer.getPlayer().getName());
                            tournoiPlayer.setWaiting(true);
                            tournoiPlayer.setOnFight(false);
                            tournoiPlayer.setDead(false);
                        }
                    }else if(playersSize == 1){
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }

                if(TournoiState.isState(TournoiState.FINAL)){
                    if (playersSize == 2){
                        if(tournoiGame == null) {
                            System.out.println(ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().size());
                            ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().forEach(tournoiPlayer -> System.out.println(tournoiPlayer.getPlayer().getName()));
                            TournoiPlayer tournoiPlayer1 = ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().get(0);
                            TournoiPlayer tournoiPlayer2 = ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().get(1);
                            List<TournoiPlayer> tournoiPlayers = new ArrayList<>();
                            tournoiPlayers.add(tournoiPlayer1);
                            tournoiPlayers.add(tournoiPlayer2);
                            tournoiGame = new TournoiGame(tournoiPlayers);
                            tournoiGame.startFight();
                        }
                    }else if(playersSize == 1){
                        MessageUtil.broadcastMessage("§eBravo à §6"+ ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().get(0).getPlayer().getName() +" §equi vient de remporter le Tournoi PvP!");
                        stopTournoi();
                    }
                }
            }
        }, 20, 20);
    }

    public void stopTournoi(){
        //TODO VERIFICATION SI IL Y A DES MATCH EN COURS ET SI OUI REGIVE LE STUFF

        ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList().removeAll(ValkyaTournoi.getInstance().getTournoiPlayersManager().getPlayersList());
        TournoiState.setState(TournoiState.NONE);
        timer = 0;
        Bukkit.getScheduler().cancelTask(task);
    }

    public boolean doesTournoiIsStart(){
        return TournoiState.getState() != TournoiState.NONE;
    }

    public void fight(){
        List<Integer> randomNumbers = getTwoRandomNumber(ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().size());
        System.out.println(ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().size());
        ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().forEach(tournoiPlayer -> System.out.println(tournoiPlayer.getPlayer().getName()));
        TournoiPlayer tournoiPlayer1 = ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().get(randomNumbers.get(0));
        TournoiPlayer tournoiPlayer2 = ValkyaTournoi.getInstance().getTournoiPlayersManager().getWaitingPlayersList().get(randomNumbers.get(1));
        List<TournoiPlayer> tournoiPlayers = new ArrayList<>();
        tournoiPlayers.add(tournoiPlayer1);
        tournoiPlayers.add(tournoiPlayer2);
        tournoiGame = new TournoiGame(tournoiPlayers);
        tournoiGame.startFight();
    }

    public List<Integer> getTwoRandomNumber(int maxPlayers){
        Random rand = new Random();
        HashSet<Integer> randomNumbers = new HashSet<Integer>();

        int number1 = rand.nextInt(maxPlayers);
        randomNumbers.add(number1);
        int number2 = rand.nextInt(maxPlayers);
        while (randomNumbers.contains(number2)) number2 = rand.nextInt(maxPlayers + 1);

        List<Integer> number = new ArrayList<>();
        number.add(number1);
        number.add(number2);

        return number;
    }

    public TournoiGame getTournoiGame() {
        return tournoiGame;
    }

    public void setTournoiGame(TournoiGame tournoiGame) {
        this.tournoiGame = tournoiGame;
    }
}
