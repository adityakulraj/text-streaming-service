# Simple HTTP Based Text Streaming Service

This is a simple HTTP Based Text Streaming service, where there are multiple providers available and the providers can be switched out dynamically based on some criteria.

## Design Overview

We'll create a server that:

- Provides a streaming endpoint for text.
- Supports multiple inference providers (e.g., different algorithms or models for processing the text).
- Allows dynamic switching between these providers based on specific criteria.

The server will be designed to stream text data to a client, which will process the text based on the currently selected inference provider.

## Available APIs

### TEXT STREAMING API

**Endpoint:** `api/stream/text?text={string}`

**Method:** GET

**Optional Header:** `[switch-provider, true]`

- This API can be called to stream text data to the server (The business logic can be plugged in here).
- If the value of `switch-provider` is set to `true`, then the provider for the streaming service will be switched out to a new random provider. This is done in case of any issues with the current provider, for example, slowness of service. The front-end or the client can simply attach this header, and the provider will be switched out to a new random one.

### SWITCH PROVIDER API

**Endpoint:** `api/stream/switch-provider?provider={providerId}`

- Use this API to manually switch the provider to the one specified by `{providerId}`.
