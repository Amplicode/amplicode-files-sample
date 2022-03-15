This branch contains an example of project to work with files (backend only)
using [spring-content](https://github.com/paulcwarren/spring-content) project.

### Case
A user has profile which stores info about user including photo metadata like content id, MIME type, size and original
file name. The file is stored in the file system and can be uploaded and downloaded for a user profile using REST API.
CRUD operations for user profiles can be executed using GraphQL API.

### Structure
The sample project consists of:

1. The `UserProfile` entity: stores info about photo like file identifier, size, MIME type and original file name. The
   file with photo is stored in the file system.
2. The `UserProfileRepository`: JPA repository for the `UserProfile` entity.
3. The `UserProfileController`: a GraphQL controller for CRUD operations with the `UserProfile` entity.
4. The `UserProfileStore` interface: allows set/get content of photo for the `UserProfile` entity. This interface
   extends the `org.springframework.content.commons.repository.ContentStore` for which there are some implementations
   provided by `spring-content` starters. Also, this  `UserProfileStore` is annotated
   with `org.springframework.content.rest.StoreRestResource`. This annotation allows upload and download a photo for
   the `UserProfile` entity using REST API endpoint `/profiles/{userProfileId}/photo`, where `{userProfileId}`
   represents an id of user profile for which that file should be uploaded.