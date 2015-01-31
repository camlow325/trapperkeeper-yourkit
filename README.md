# trapperkeeper-yourkit

A trapperkeeper web application which can be used to programmatically generate
memory snapshots for use with the YourKit Java Profiler.  See
https://www.yourkit.com/.

## Usage

This service depends upon two libraries from the YourKit Java Profiler -
libyjpagent and yjp-controller-api-redist.  See
https://www.yourkit.com/java/profiler.  Before running this service, you will
need to link to or copy these library files into the _lib_ subdirectory
where you have cloned this repository.

Assuming you have installed the YourKit profiler under
_/opt/yjp-2014-build-14116-linux_ on Linux, for example, you could create the
following symlinks:

~~~
ln -fs /opt/yjp-2014-build-14116-linux/bin/linux-x86-64/libyjpagent.so ./lib/libyjpagent.lib

ln -fs /opt/yjp-2014-build-14116-linux/lib/yjp-controller-api-redist.jar ./lib/yjp-controller-api-redist.jar
~~~

Assuming you have installed the YourKit profiler under
_/Applications/YourKit_Java_Profiler_2014_build_14116.app_ on a Mac, for
example, you could create the following symlinks:

~~~
ln -fs /Applications/YourKit_Java_Profiler_2014_build_14116.app/Contents/Resources/bin/mac/libyjpagent.jnilib ./lib/libyjpagent.lib

ln -fs /Applications/YourKit_Java_Profiler_2014_build_14116.app/Contents/Resources/lib/yjp-controller-api-redist.jar ./lib/yjp-controller-api-redist.jar
~~~

To start the service, run:

`lein tk`

Then, to create a snapshot, visit:

http://localhost:8080/yourkit/snapshot

## License

Distributed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
