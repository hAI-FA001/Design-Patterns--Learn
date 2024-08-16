// helps when we have an object inside itself (e.g. Directory can have another Directory, delivery box inside delivery box)
// when drawn, it's tree-like (nodes are the Object itself, leaves are types other than the Object (where the composition ends))

package structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {
    public static void main(String[] args) {
        Directory parentDir = new Directory();
        FileSystem fileObj1 = new File();
        parentDir.add(fileObj1);

        Directory childDir = new Directory();
        FileSystem fileObj2 = new File();
        childDir.add(fileObj2);

        parentDir.add(childDir);

        parentDir.ls();
    }
}

// the leaf
class File implements FileSystem {
    @Override
    public void ls() {
        System.out.println("Listing File " + hashCode());
    }
}

// the composite type
class Directory implements FileSystem {
    List<FileSystem> fileSystemList = new ArrayList<>();

    @Override
    public void ls() {
        System.out.println("Listing Directory " + hashCode());
        for (FileSystem fileSystem : fileSystemList) {
            fileSystem.ls();
        }
    }

    void add(FileSystem fs) {
        fileSystemList.add(fs);
    }
}

interface FileSystem {
    void ls();
}
