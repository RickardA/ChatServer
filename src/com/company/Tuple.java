package com.company;

public class Tuple<LeftClass, RightClass> {
    public final LeftClass senderSocketAddress;
    public final RightClass object;

    public Tuple(LeftClass senderSocketAdress, RightClass object) {
        this.senderSocketAddress = senderSocketAdress;
        this.object = object;
    }

    @Override
    public int hashCode() {
        return senderSocketAddress.hashCode() + object.hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        final Tuple other = (Tuple) obj;
        return senderSocketAddress.equals(other.senderSocketAddress) && object.equals(other.object);
    }
}