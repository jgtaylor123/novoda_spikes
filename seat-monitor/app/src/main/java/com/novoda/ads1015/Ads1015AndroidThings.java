package com.novoda.ads1015;

import com.google.android.things.pio.I2cDevice;

import java.io.IOException;

class Ads1015AndroidThings implements Ads1015 {

    private final ChannelReader channelReader;
    private final ChannelComparator channelComparator;
    private final I2cDevice device;

    Ads1015AndroidThings(ChannelReader channelReader, ChannelComparator channelComparator, I2cDevice device) {
        this.channelReader = channelReader;
        this.channelComparator = channelComparator;
        this.device = device;
    }

    @Override
    public int read(Channel channel) {
        return channelReader.read(channel);
    }

    @Override
    public void startComparator(Channel channel, int thresholdInMv, ComparatorCallback callback) {
        channelComparator.startComparator(channel, thresholdInMv, callback);
    }

    @Override
    public void close() {
        try {
            device.close();
            channelComparator.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
