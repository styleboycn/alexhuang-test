package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.state.VoteManager;

public class StateTest {

    public static void main(String[] args) {

        VoteManager vm = new VoteManager();
        for (int i = 0; i < 9; i++) {
            vm.vote("u1","A");
        }
    }

}
