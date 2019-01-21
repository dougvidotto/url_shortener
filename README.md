This simple URL shortener uses PostgreSQL as database. So it should be installed in your machine to run it.
After installing PostgreSQL, the database url_shortener should be created.

Then, the file persistence.xml should be changed to the corresponded user and password of the database

For this API, there are two public methods at URLShortener class. These two methods creates a short version of a URL and also
returns the original version of a URL, given the short one.
