package vokra.vokraapp.services.websitefetcher;

enum VokraDbColumn
{
    ID(0),
    NAME(1),
    AGE(2),
    GENDER(3),
    COLOUR(4),
    FOSTER(5),
    CITY(6),
    NOTES(7),
    CREATED(8);

    private Integer hierarchy;

    VokraDbColumn(final Integer hierarchy)
    {
        this.hierarchy = hierarchy;
    }

    int getHierarchy()
    {
        return hierarchy;
    }


}
