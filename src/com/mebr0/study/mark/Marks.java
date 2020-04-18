package com.mebr0.study.mark;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class for marking {@link com.mebr0.user.entity.Student} within {@link com.mebr0.study.Course}
 * Contains three parts: two attestations and final
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Marks implements Serializable {

    private float attestation1;
    private float attestation2;
    private float finale;

    private static final byte ATTESTATION_LIMIT = 30;
    private static final byte FINAL_LIMIT = 40;

    public boolean updateMark(float value, Mode mode) {

        switch (mode) {
            case ATT1:
                return addAttestation1(value);
            case ATT2:
                return addAttestation2(value);
            case FINAL:
                return addFinal(value);
        }

        return false;
    }

    private boolean addAttestation1(float value) {
        if (attestation1 + value > ATTESTATION_LIMIT && attestation1 + value < 0)
            return false;

        this.attestation1 += value;
        return true;
    }

    private boolean addAttestation2(float value) {
        if (attestation2 + value > ATTESTATION_LIMIT && attestation2 + value < 0)
            return false;

        this.attestation2 += value;
        return true;
    }

    private boolean addFinal(float value) {
        if (finale + value > FINAL_LIMIT && finale + value < 0)
            return false;

        this.finale += value;
        return true;
    }

    public float getAttestation1() {
        return attestation1;
    }

    public float getAttestation2() {
        return attestation2;
    }

    public float getFinal() {
        return finale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Marks))
            return false;

        Marks marks = (Marks) o;
        return Float.compare(marks.attestation1, attestation1) == 0 &&
                Float.compare(marks.attestation2, attestation2) == 0 &&
                Float.compare(marks.finale, finale) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attestation1, attestation2, finale);
    }

    @Override
    public String toString() {
        return this.attestation1 + " | " + this.attestation2 + " | " + this.finale;
    }

    public enum Mode {
        ATT1,
        ATT2,
        FINAL;
    }
}
