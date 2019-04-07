/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.sqs.testkit
import java.util.Optional

import akka.stream.alpakka.sqs.{MessageAction, SqsAckResult, SqsPublishResult}
import software.amazon.awssdk.core.SdkPojo
import software.amazon.awssdk.services.sqs.model.{SendMessageRequest, SqsResponse, SqsResponseMetadata}

import scala.compat.java8.OptionConverters._

/**
 * Message factory class for testing purposes
 */
object MessageFactory {

  def createSqsPublishResult[T <: SdkPojo](responseMetadata: SqsResponseMetadata,
                                           metadata: T,
                                           request: SendMessageRequest): SqsPublishResult[T] =
    new SqsPublishResult(responseMetadata, metadata, request)

  def createSqsAckResult[T <: SdkPojo](responseMetadata: Option[SqsResponseMetadata],
                                       metadata: Option[T],
                                       messageAction: MessageAction): SqsAckResult[T] =
    new SqsAckResult(responseMetadata, metadata, messageAction)

  /** Java API */
  def createSqsAckResult[T <: SqsResponse](responseMetadata: Optional[SqsResponseMetadata],
                                           metadata: Optional[T],
                                           messageAction: MessageAction): SqsAckResult[T] =
    new SqsAckResult(responseMetadata.asScala, metadata.asScala, messageAction)

  def createSqsAckResult[T <: SdkPojo](messageAction: MessageAction): SqsAckResult[T] =
    new SqsAckResult(messageAction)
}
