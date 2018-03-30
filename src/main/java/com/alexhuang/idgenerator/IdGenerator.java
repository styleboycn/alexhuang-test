/*
 * Copyright (c) 2014, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.idgenerator;

import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator implements IIdGenerator {

    private static final byte genflg = 0; // 0.server; 1.app
    private static byte PICKUP = 0;
    private static byte DELIVERY = 1;
    private static byte DELIVERY_PKG = 2;

    public String gen(byte taskType) {
        UUID uuid = UUID.randomUUID();
        byte[] data = new byte[26];
        write(data, 0, System.currentTimeMillis());
        write(data, 8, uuid.getMostSignificantBits());
        write(data, 16, uuid.getLeastSignificantBits());
        data[24] = genflg;
        data[25] = taskType;
        return IDCoder.encode(data);
    }

    public String genPickupId() {
        return gen(PICKUP);
    }

    public String genDeliveryId() {
        return gen(DELIVERY);
    }

    @Override
    public String genDeliveryPkgId() {
        return gen(DELIVERY_PKG);
    }

    private void write(byte[] data, int start, long value) {
        data[start + 0] = (byte) (value >>> 56);
        data[start + 1] = (byte) (value >>> 48);
        data[start + 2] = (byte) (value >>> 40);
        data[start + 3] = (byte) (value >>> 32);
        data[start + 4] = (byte) (value >>> 24);
        data[start + 5] = (byte) (value >>> 16);
        data[start + 6] = (byte) (value >>> 8);
        data[start + 7] = (byte) (value >>> 0);
    }
    
    public String gen() {
        UUID uuid = UUID.randomUUID();
        byte[] data = new byte[24];
        write(data, 0, System.currentTimeMillis());
        write(data, 8, uuid.getMostSignificantBits());
        write(data, 16, uuid.getLeastSignificantBits());
        return Base64.getEncoder().encodeToString(data);
    }
    
    public static void main(String[] args) {
    	System.out.println(new IdGenerator().genPickupId());
    }
}
