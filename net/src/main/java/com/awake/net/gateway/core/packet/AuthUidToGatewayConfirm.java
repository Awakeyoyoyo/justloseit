package com.awake.net.gateway.core.packet;

import com.awake.net.packet.common.ModuleConstant;
import com.awake.net.protocol.anno.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Ignore;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/**
 * @version : 1.0
 * @ClassName: AuthUidToGatewayConfirm
 * @Description: TODO
 * @Auther: awake
 * @Date: 2023/10/11 17:55
 **/

@Packet(protocolId = AuthUidToGatewayConfirm.PROTOCOL_ID, moduleId = ModuleConstant.GATEWAY_MODULE_ID)
@ProtobufClass
@Data
public class AuthUidToGatewayConfirm {
    @Ignore
    public static final short PROTOCOL_ID = 21;

    private long uid;

    public static AuthUidToGatewayConfirm valueOf(long uid) {
        var authUidToGateway = new AuthUidToGatewayConfirm();
        authUidToGateway.uid = uid;
        return authUidToGateway;
    }
}
