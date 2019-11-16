package com.alexhuang.patterndesign.state;

import java.util.Map;

public class SpiteVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //恶意投票，取消投票记录
        String str = voteManager.getMapVoteResult().get(user);
        if (str != null)
            voteManager.getMapVoteResult().remove(user
            );
        System.out.println("你有恶意刷屏行为，取消投票资格");
    }
}
