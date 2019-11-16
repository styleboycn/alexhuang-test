package com.alexhuang.patterndesign.state;

public class NormalVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //正常投票，记录到投票记录中
        voteManager.getMapVoteResult().put(user, voteItem);
        System.out.println("恭喜投票成功");
    }

}
