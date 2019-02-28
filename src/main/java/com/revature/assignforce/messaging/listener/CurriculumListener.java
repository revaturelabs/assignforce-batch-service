//package com.revature.assignforce.messaging.listener;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import com.rabbitmq.client.Channel;
//import com.revature.assignforce.beans.Batch;
//import com.revature.assignforce.service.BatchService;
//
//@Component
//public class CurriculumListener {
//
//	private BatchService batchService;
//
//	private final String batchQueue;
//
//	@RabbitListener(bindings = @QueueBinding(
//			value = @Queue(value = "batch-queue", durable = "true"),
//			exchange = @Exchange(value = "assignforce", ignoreDeclarationExceptions = "true"),
//			key = "assignforce.curriculum.delete")
//	)
//	public void receiveCurriculum(final Integer curriculumId, Channel channel,
//				@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
//		try {
//			//get all batches that refer to curriculum
//			List<Batch> batchList = batchService.getAllByCurriculum(curriculumId);
//			//iterate through and set it to null
//			batchList.forEach((batch) -> batch.setCurriculum(null));
//			//save
//			batchList.forEach((batch) -> batchService.update(batch));
//			//send acknowledgement
//			channel.basicAck(tag, false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public CurriculumListener(BatchService batchService,
//			@Value("${spring.rabbitmq.batch-queue:batch-queue}") String batchQueue) {
//		super();
//		this.batchService = batchService;
//		this.batchQueue = batchQueue;
//	}
//}
