This branch contains an example of a project to work with files (backend only). Implementation is based on analysis Jira
REST API and YouTrack REST API.

### Case

An entity for the project stores metadata for icon and a list of files. All files are stored in the file system. Files
can be uploaded and downloaded for a project using REST API. CRUD operations for a project can be executed using GraphQL
API.

### Structure

The sample project consists of:

1. The `Project` entity stores metadata for the icon file and list of documents: like file identifier, size, MIME-type ,
   and original file name. All files are stored in the file system.
2. DTO for the `Project` entity: `ProjectDto` excludes information about the icon.
3. POJO class for file metadata: `LocalFileRef`.
4. The `ProjectRepository`: JPA repository for the `Project` entity.
5. The `ProjectController`: a GraphQL controller for CRUD operations with the `Project` entity uses the `ProjectDto` as
   input and output.
6. `ProjectMapper` - a mapper for the `Project` entity and its DTO -`ProjectDto`
7. `LocalFileStorage` - file storage implementation, contains the methods to set/get content using `LocalFileRef`.
8. Two REST controllers: `ProjectFileController` - to upload/download icon for the project, to upload attachments for
   the project, `FileController`
    - to download attachments using a path.

To create a project with file(s) it is required to create a main entity using GraphQL API and upload file(s) for a
created project using REST API.
