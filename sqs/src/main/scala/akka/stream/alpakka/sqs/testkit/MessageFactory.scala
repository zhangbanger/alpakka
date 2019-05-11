/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.sqs.testkit
import akka.stream.alpakka.sqs.{MessageAction, SqsPublishResult, SqsPublishResultEntry}
import akka.stream.alpakka.sqs.SqsAckResult.{SqsChangeMessageVisibilityResult, SqsDeleteResult}
import akka.stream.alpakka.sqs.SqsAckResultEntry.{SqsChangeMessageVisibilityResultEntry, SqsDeleteResultEntry}
import software.amazon.awssdk.awscore.DefaultAwsResponseMetadata
import software.amazon.awssdk.services.sqs.model._

/**
 * Message factory class for testing purposes
 */
object MessageFactory {

  val EmptySqsResponseMetadata: SqsResponseMetadata =
    SqsResponseMetadata.create(DefaultAwsResponseMetadata.create(java.util.Collections.emptyMap()))

  def createPublishResult(request: SendMessageRequest, response: SendMessageResponse): SqsPublishResult =
    new SqsPublishResult(request, response)

  def createPublishResultEntry(
      request: SendMessageRequest,
      result: SendMessageBatchResultEntry,
      responseMetadata: SqsResponseMetadata = EmptySqsResponseMetadata
  ): SqsPublishResultEntry =
    new SqsPublishResultEntry(request, result, responseMetadata)

  def createDeleteResult(messageAction: MessageAction.Delete, response: DeleteMessageResponse): SqsDeleteResult =
    new SqsDeleteResult(messageAction, response)

  def createChangeMessageVisibilityResult(messageAction: MessageAction.ChangeMessageVisibility,
                                          response: ChangeMessageVisibilityResponse): SqsChangeMessageVisibilityResult =
    new SqsChangeMessageVisibilityResult(messageAction, response)

  def createDeleteResultEntry(messageAction: MessageAction.Delete,
                              result: DeleteMessageBatchResultEntry,
                              responseMetadata: SqsResponseMetadata = EmptySqsResponseMetadata): SqsDeleteResultEntry =
    new SqsDeleteResultEntry(messageAction, result, responseMetadata)

  def createChangeMessageVisibilityResultEntry(
      messageAction: MessageAction.ChangeMessageVisibility,
      result: ChangeMessageVisibilityBatchResultEntry,
      responseMetadata: SqsResponseMetadata = EmptySqsResponseMetadata
  ): SqsChangeMessageVisibilityResultEntry =
    new SqsChangeMessageVisibilityResultEntry(messageAction, result, responseMetadata)
}
