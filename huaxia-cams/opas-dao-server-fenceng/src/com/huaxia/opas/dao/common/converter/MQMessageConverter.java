package com.huaxia.opas.dao.common.converter;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.huateng.neofp.IcsRuntimeException;
import com.huateng.neofp.channel.http.support.JsonUtils;
import com.huaxia.opas.dao.common.OpasConstants;

/**
 * MQ报文转化器
 * 
 * @author uatym990190
 */
@SuppressWarnings("rawtypes")
public class MQMessageConverter extends SimpleMessageConverter {

	private static Logger log = LoggerFactory.getLogger(MQMessageConverter.class);

	/**
	 * 组包
	 */
	public MapMessage createMessageForMap(Map map, Session session) throws JMSException {
		MapMessage message = null;
		try {
			message = session.createMapMessage();
			message.setString(OpasConstants.MQ_PACKET,
					new String(JsonUtils.jsonFromObject(map, OpasConstants.UTF8), OpasConstants.UTF_8));

			if (log.isDebugEnabled()) {
				log.debug("toMessage>>>" + message.getString(OpasConstants.MQ_PACKET));
			}
		} catch (Exception e) {
			throw new IcsRuntimeException("MQToMessage Conveter error!", e);
		}

		return message;
	}

	/**
	 * 解包
	 */
	@SuppressWarnings("unchecked")
	public Map extractMapFromMessage(MapMessage message) throws JMSException {
		Map map = JsonUtils.objectFromJson(message.getString(OpasConstants.MQ_PACKET), Map.class);

		Map result = new HashMap();

		boolean hasHead = map.containsKey(OpasConstants.MQ_HEAD);
		boolean hasBody = map.containsKey(OpasConstants.MQ_BODY);

		if (hasHead) {
			result.putAll((Map) map.get(OpasConstants.MQ_HEAD));
		}

		if (hasBody) {
			result.putAll((Map) map.get(OpasConstants.MQ_BODY));
		}

		if (!hasHead) {
			result.putAll(map);
		}

		if (log.isDebugEnabled()) {
			log.debug("formatMessage>>>" + result);
		}

		return result;
	}
}
