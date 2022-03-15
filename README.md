This branch contains an example of project to work with files (backend only)
using [spring-content](https://github.com/paulcwarren/spring-content) project.

### Case

An entity for user profile stores metadata for two files. One of these files is stored in the file system, the second
one is stored as BLOB d the database. Both files can be uploaded and downloaded for a user profile using REST API. CRUD
operations for user profiles can be executed using GraphQL API.

### Structure

The sample project consists of:

1. The `UserProfile` entity stores metadata about two files: like file identifier, size, MIME type and original file
   name. One file is stored in the file system, the second one - as BLOB in the database.
2. POJO class for file metadata: `FileRef`.
3. The `UserProfileRepository`: JPA repository for the `UserProfile` entity.
4. The `UserProfileController`: a GraphQL controller for CRUD operations with the `UserProfile` entity.
5. The `FileRefFsStore` interface: allows set/get content for the `FsFileRef` instance. This interface extends
   the `org.springframework.content.fs.store.FilesystemContentStore` for which there is a default implementation
   provided by `spring-content-fs` project.
6. The `FileRefJpaStore` interface: allows set/get content for the `JpaFileRef` instance. This interface extends
   the `org.springframework.content.jpa.store.JpaContentStore` for which there is a default implementation provided
   by `spring-content-jpa` project.
7. Two REST controllers: `FsFileRefController` - to upload/download file to/from the file system, `JpaFileRefController`
   - to upload/download file to/from the database.

To create a user profile with file(s) it is required to upload file(s) using REST API and create a main entity with
specified metadata for stored files using GraphQL API.