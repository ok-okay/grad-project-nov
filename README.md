# grad-project-nov
Postman link for APIs: https://www.postman.com/security-pilot-12595993/workspace/nextleap/collection/30687522-38bdecb4-4f8a-4262-961d-23c78ba4f8a4

## getContentsFromType
**http://15.206.178.60:8080/v1/series**

This API fetches the contents grouped according to their collection for the /home, /movies, /series routes in hotstar.
The API endpoint is /v1/contentType

The allowed values for contentType are "series" and "movies" for now.﻿

## getContentFromIdentifiers
**http://15.206.178.60:8080/v1/series/loki/2**

This API fetches the content based on the contentId provided for the content page.

The API route is in sync with hotstar, /v1/contentType/contentName/contentId

The allowed values for contentType are "series" and "movies" for now.

contentName is just a reference param, not being used for anything.

contentId is a long data type used to fetch the content from DB.

The data is available for contentId values 1 and 2 for now.

## getEpisodesFromSeasonNumber
**http://15.206.178.60:8080/v1/2/1**

This API fetches the episodes for a specific season of a content based on the contentId and seasonNumber provided.

The API route is /v1/contentId/seasonNumber

contentId is a long data type and seasonNumber is an int data type used to fetch the episodes from DB.

The data is available for contentId values 2, seasonNumber 1 and 2 for now.
