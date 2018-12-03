 package balik.console.impl;

class VEBNode
{
    int universeSize;
    int min;
    int max;
    VEBNode summary;
    VEBNode[] cluster;

    VEBNode(int universeSize)
    {
        this.universeSize = universeSize;
        min = VEBTreeImpl.NULL;
        max = VEBTreeImpl.NULL;

        /* Allocate the summary and cluster children. */
        initializeChildren(universeSize);
    }

    private void initializeChildren(int universeSize)
    {
        if(universeSize <= VEBTreeImpl.BASE_SIZE)
        {
            summary = null;
            cluster = null;
        }
        else
        {
            int childUniverseSize = higherSquareRoot();

            summary = new VEBNode(childUniverseSize);
            cluster = new VEBNode[childUniverseSize];

            for(int i = 0; i < childUniverseSize; i++)
            {
                cluster[i] = new VEBNode(childUniverseSize);
            }
        }
    }

    /**
     * Returns the value of the most significant bits of x.
     **/
    private int higherSquareRoot()
    {
        return (int)Math.pow(2, Math.ceil((Math.log10(universeSize) / Math.log10(2)) / 2));
    }
}

