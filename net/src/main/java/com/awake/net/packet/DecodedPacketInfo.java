package com.awake.net.packet;

import lombok.Data;
import org.springframework.lang.Nullable;

/**
 * @version : 1.0
 * @ClassName: DecodedPacketInfo
 * @Description: TODO
 * @Auther: awake
 * @Date: 2023/8/3 19:41
 **/
@Data
public class DecodedPacketInfo {

    /**
     * 解码后的包
     */
    private IPacket packet;

    /**
     * 解码后的包的附加包
     */
    private IAttachment attachment;

    /**
     * 长度
     */
    private int length;
    /**
     * 加密所用时间
     */
    private long encodedTime;

    public static DecodedPacketInfo valueOf(IPacket packet, @Nullable IAttachment attachment) {
        DecodedPacketInfo packetInfo = new DecodedPacketInfo();
        packetInfo.packet = packet;
        packetInfo.attachment = attachment;
        return packetInfo;
    }
}