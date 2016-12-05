package rdfbones.lib;

import java.util.ArrayList;
import java.util.List;

import rdfbones.form.ExistingInstanceSelector;
import rdfbones.form.Form;
import rdfbones.form.FormElement;
import rdfbones.form.Selector;
import rdfbones.form.SubformAdder;
import rdfbones.rdfdataset.Constant;
import rdfbones.rdfdataset.ExistingInstance;
import rdfbones.rdfdataset.ExistingRestrictionTriple;
import rdfbones.rdfdataset.FormInputNode;
import rdfbones.rdfdataset.Graph;
import rdfbones.rdfdataset.GreedyRestrictionTriple;
import rdfbones.rdfdataset.InputNode;
import rdfbones.rdfdataset.MainInputNode;
import rdfbones.rdfdataset.MultiTriple;
import rdfbones.rdfdataset.RDFNode;
import rdfbones.rdfdataset.RestrictionTriple;
import rdfbones.rdfdataset.Triple;

public class TripleLib {

  public static Form sdeForm() {

    Form measDatumSubForm = new Form("Measurement Datum");

    FormElement categoricalLabel = new Selector("categoricalLabel", "");
    measDatumSubForm.formElements.add(categoricalLabel);

    SubformAdder measurementDatum =
        new SubformAdder("measurementDatum", "Measurement Type", "measurementDatumType");
    measurementDatum.subForm = measDatumSubForm;

    FormElement boneOrgan = new ExistingInstanceSelector("boneOrgan", "Bone Segment");

    Form assySubForm = new Form("Assays");
    assySubForm.formElements.add(boneOrgan);
    assySubForm.formElements.add(measurementDatum);

    SubformAdder assayType =
        new SubformAdder("specimenCollectionProcess", "Assays", "assayType");
    assayType.subForm = assySubForm;
    Form mainForm = new Form("Study Design Execution");
    mainForm.formElements.add(assayType);

    return mainForm;
  }

  public static List<Triple> sdeDataTiples() {

    List<Triple> triple = new ArrayList<Triple>();
    triple.add(new Triple(new MainInputNode("subject"), "obo:BFO_0000051", "object"));
    triple
        .add(new MultiTriple("object", "obo:BFO_0000051", "specimenCollectionProcess"));
    triple.add(new MultiTriple("specimenCollectionProcess", "obo:OBI_0000293",
        new ExistingInstance("boneOrgan")));
    triple.add(new Triple("specimenCollectionProcess", "obo:OBI_0000299", "specimen"));
    triple.add(new Triple("assay", "obo:OBI_0000293", "specimen"));
    triple.add(new MultiTriple("assay", "obo:OBI_0000299", "measurementDatum"));
    triple.add(new Triple("measurementDatum", "obo:IAO_0000299", new FormInputNode(
        "categoricalLabel")));
    return triple;
  }

  public static List<Triple> sdeSchemeTriples() {

    List<Triple> triple = new ArrayList<Triple>();
    triple.add(new RestrictionTriple("subjectType", "obo:BFO_0000051",
        "studyDesignExecutionType"));
    triple.add(new RestrictionTriple("studyDesignExecutionType", "obo:BFO_0000051",
        new InputNode("assayType")));
    triple.add(new RestrictionTriple(new FormInputNode("assayType"), "obo:OBI_0000293",
        "specimenType"));
    triple.add(new RestrictionTriple("specimenCollectionProcessType", "obo:OBI_0000299",
        "specimenType"));
    triple.add(new RestrictionTriple("specimenCollectionProcessType", "obo:OBI_0000293",
        "entireBonyPart"));
    triple.add(new RestrictionTriple("entireBonyPart", "obo-fma:regional_part_of",
        "bonyPart", "owl:allValuesFrom"));
    triple.add(new RestrictionTriple("bonyPart", "obo-fma:constitutional_part_of",
        "boneOrganType", "owl:someValuesFrom"));
    triple.add(new RestrictionTriple("assayType", "obo:OBI_0000299", new FormInputNode(
        "measurementDatumType")));
    triple.add(new GreedyRestrictionTriple(new FormInputNode("measurementDatumType"),
        "obo:OBI_0000999", "categoricalLabelType"));
    // "categoricalLabelType", "owl:onClass"));
    triple.addAll(sdeschemeTriplesSubClasses());
    triple.addAll(sdeschemeTriplesTypes());
    return triple;
  }

  public static List<Triple> sdeschemeTriplesSubClasses() {

    List<Triple> triple = new ArrayList<Triple>();
    triple.add(new Triple("studyDesignExecutionType", "rdfs:subClassOf", new Constant(
        "obo:OBI_0000471")));
    triple.add(new Triple("specimenCollectionProcessType", "rdfs:subClassOf",
        new Constant("obo:OBI_0000659")));
    triple.add(new Triple("assayType", "rdfs:subClassOf",
        new Constant("obo:OBI_0000070")));
    // triple.add(new Triple("measurementDatumType", "rdfs:subClassOf",
    // new Constant("obo:OBI_0000070MDType")));
    return triple;
  }

  public static List<Triple> sdeschemeTriplesTypes() {

    List<Triple> triple = new ArrayList<Triple>();
    triple.add(new Triple(new MainInputNode("subject"), "rdf:type", "subjectType"));
    triple.add(new Triple("assay", "rdf:type", new FormInputNode("assayType")));
    triple.add(new Triple("specimen", "rdf:type", "specimenType"));
    triple.add(new Triple("specimenCollectionProcess", "rdf:type",
        "specimenCollectionProcessType"));
    triple.add(new Triple("measurementDatum", "rdf:type", new FormInputNode(
        "measurementDatumType")));
    triple.add(new Triple("object", "rdf:type", "studyDesignExecutionType"));
    triple.add(new ExistingRestrictionTriple(new InputNode("boneOrgan"), "rdf:type",
        "boneOrganType"));
    triple.add(new ExistingRestrictionTriple(new InputNode("categoricalLabel"),
        "rdf:type", "categoricalLabelType"));
    return triple;
  }

  public static Form csrForm() {

    Selector catLabelSelector =  new Selector("categoricalLabel");
    Form measurementForm = new Form("");
    measurementForm.style = "inline";
    measurementForm.formElements.add(catLabelSelector);

    SubformAdder boneOrganFormElement = new SubformAdder("boneOrganType", "BoneOrgans");
    boneOrganFormElement.style = "inline";
    boneOrganFormElement.subForm = measurementForm;
    Form boneOrganForm = new Form("");
    boneOrganForm.formElements.add(boneOrganFormElement);

    SubformAdder skeletalRegion =
        new SubformAdder("skeletalRegionType", "Skeletal Regions");
    skeletalRegion.subForm = boneOrganForm;
    Form mainForm = new Form("Skull");
    mainForm.formElements.add(skeletalRegion);
    return mainForm;
  }

  public static List<Triple> csrDataTriples() {

    List<Triple> triples = new ArrayList<Triple>();
    RDFNode subject = new MainInputNode("subject");

    triples.add(new Triple(subject, "rdfbones:hasCoherentSkeletalRegion", "object"));
    triples.add(new Triple(subject, "obo:BFO_0000051", "measurementDatum", false));
    triples.add(new Triple("measurementDatum", "obo:IAO_0000299", new FormInputNode(
        "categoricalLabel")));
    triples
        .add(new Triple("measurementDatum", "obo:IAO_0000136", "bonyPartSegment"));
    triples.add(new Triple("bonyPartSegment", "obo-fma:regional_part_of",
        "bonyPart"));
    triples.add(new Triple("bonyPart", "obo-fma:constitutional_part_of", "boneOrgan"));
    triples.add(new MultiTriple("boneOrgan", "obo-fma:systemic_part_of",
        "skeletalRegion"));
    triples.add(new MultiTriple("skeletalRegion", "obo-fma:systemic_part_of", "object"));
    return triples;
  }

  public static List<Triple> csrSchemeTriples() {

    List<Triple> triples = new ArrayList<Triple>();
    // RestrictionTriples
    triples.add(new RestrictionTriple("skeletalInventoryType", "obo:BFO_0000051",
        "measurementDatumType", "owl:someValuesFrom"));
    triples.add(new GreedyRestrictionTriple("measurementDatumType",
        "obo:OBI_0000999", "categoricalLabelType", "owl:allValuesFrom"));
    triples.add(new RestrictionTriple("measurementDatumType", "obo:IAO_0000136",
        "bonyPartSegmentType", "owl:allValuesFrom"));
    triples.add(new RestrictionTriple("bonyPartSegmentType", "obo-fma:regional_part_of",
        "bonyPartType", "owl:allValuesFrom"));
    triples.add(new RestrictionTriple("bonyPartType",
        "obo-fma:constitutional_part_of", new FormInputNode("boneOrganType"),
        "owl:someValuesFrom"));
    triples.add(new RestrictionTriple(new FormInputNode("boneOrganType"),
        "obo-fma:systemic_part_of", new FormInputNode("skeletalRegionType"),
        "owl:someValuesFrom"));
    triples
        .add(new RestrictionTriple(new FormInputNode("skeletalRegionType"),
            "obo-fma:systemic_part_of", new MainInputNode("rangeUri"),
            "owl:someValuesFrom"));
    triples.addAll(csrTypeTriples());
    return triples;
  }

  public static List<Triple> csrTypeTriples() {

    List<Triple> triples = new ArrayList<Triple>();
    triples.add(new Triple("object", "rdf:type", new MainInputNode("rangeUri")));
    triples.add(new Triple("skeletalRegion", "rdf:type", new FormInputNode(
        "skeletalRegionType")));
    triples.add(new Triple("boneOrgan", "rdf:type", new FormInputNode(
        "boneOrganType")));
    triples.add(new Triple("bonyPart", "rdf:type", "bonyPartType"));
    triples.add(new Triple("bonyPartSegment", "rdf:type", "bonyPartSegmentType"));
    triples.add(new Triple("measurementDatum", "rdf:type", "measurementDatumType"));
    triples.add(new Triple(new MainInputNode("subject"), "rdf:type",
        "skeletalInventoryType"));
    triples.add(new ExistingRestrictionTriple(new InputNode("categoricalLabel"),
        "rdf:type", "categoricalLabelType"));
    return triples;
  }

  public static Form srForm() {

    SubformAdder measurementFormElement =
        new SubformAdder("measurementDatumType", "Categorical Label");
    Form measurementForm = new Form("");
    measurementForm.formElements.add(measurementFormElement);

    /*
    SubformAdder boneSegmentFormElement =
        new SubformAdder("bonyPartSegmentType", "Bone Segment");
    boneSegmentFormElement.subForm = measurementForm;
    Form boneSegmentForm = new Form("");
    boneSegmentForm.formElements.add(boneSegmentFormElement);
    */
    SubformAdder boneOrganFormElement = new SubformAdder("boneOrganType", "BoneOrgans");
    boneOrganFormElement.subForm = measurementForm;
    Form boneOrganForm = new Form("");
    boneOrganForm.formElements.add(boneOrganFormElement);

    SubformAdder skeletalRegion =
        new SubformAdder("skeletalRegionType", "Skeletal Regions");
    skeletalRegion.subForm = boneOrganForm;
    Form mainForm = new Form("Skull");
    mainForm.formElements.add(skeletalRegion);
    return mainForm;
  }

  public static List<Triple> srDataTriples() {

    List<Triple> triples = new ArrayList<Triple>();

    triples.add(new MultiTriple("object", "rdf:type", new FormInputNode(
        "skeletalRegionType")));
    triples.add(new MultiTriple("boneOrgan", "rdf:type", new FormInputNode(
        "boneOrganType")));
    triples.add(new Triple("bonyPart", "rdf:type", "bonyPartType"));
    triples.add(new Triple("bonyPartSegment", "rdf:type", "bonyPartSegmentType"));
    triples.add(new Triple("measurementDatum", "rdf:type", "measurementDatumType"));
    triples.add(new Triple("subject", "rdf:type", "skeletalInventoryType", false));
    return null;
  }

  public static List<Triple> srSchemeTriples() {
    List<Triple> triples = new ArrayList<Triple>();
    // RestrictionTriples
    triples.add(new RestrictionTriple("skeletalInventoryType", "obo:BFO_0000051",
        new FormInputNode("measurementDatumType"), "owl:someValuesFrom"));
    triples.add(new RestrictionTriple("measurementDatumType", "obo:IAO_0000136",
        new FormInputNode("bonyPartSegmentType"), "owl:allValuesFrom"));
    triples.add(new RestrictionTriple("bonyPartSegmentType", "obo-fma:regional_part_of",
        new FormInputNode("bonyPartType"), "owl:allValuesFrom"));
    triples.add(new RestrictionTriple(new FormInputNode("bonyPartType"),
        "obo-fma:constitutional_part_of", new FormInputNode("boneOrganType"),
        "owl:someValuesFrom"));
    triples.add(new RestrictionTriple(new FormInputNode("boneOrganType"),
        "obo-fma:systemic_part_of", new FormInputNode("skeletalRegionType"),
        "owl:someValuesFrom"));
    triples
        .add(new RestrictionTriple(new FormInputNode("skeletalRegionType"),
            "obo-fma:systemic_part_of", new MainInputNode("rangeUri"),
            "owl:someValuesFrom"));
    triples.addAll(srTypeTriples());
    return triples;
  }

  public static List<Triple> srTypeTriples() {

    List<Triple> triples = new ArrayList<Triple>();
    triples.add(new MultiTriple("object", "rdf:type", new FormInputNode(
        "skeletalRegionType")));
    triples.add(new MultiTriple("boneOrgan", "rdf:type", new FormInputNode(
        "boneOrganType")));
    triples.add(new Triple("bonyPart", "rdf:type", "bonyPartType"));
    triples.add(new Triple("bonyPartSegment", "rdf:type", "bonyPartSegmentType"));
    triples.add(new Triple("measurementDatum", "rdf:type", "measurementDatumType"));
    triples.add(new Triple(new MainInputNode("subject"), "rdf:type",
        "skeletalInventoryType"));
    return triples;
  }

  public static Form sbForm() {

    SubformAdder measurementFormElement =
        new SubformAdder("measurementDatumType", "Bone Segment");
    Form measurementForm = new Form("");
    measurementForm.formElements.add(measurementFormElement);

    SubformAdder boneSegmentFormElement =
        new SubformAdder("bonyPartSegmentType", "Bone Segment");
    boneSegmentFormElement.subForm = measurementForm;
    Form boneSegmentForm = new Form("");
    boneSegmentForm.formElements.add(boneSegmentFormElement);

    SubformAdder boneOrganFormElement = new SubformAdder("boneOrganType", "BoneOrgans");
    boneOrganFormElement.subForm = boneSegmentForm;
    Form boneOrganForm = new Form("");
    boneOrganForm.formElements.add(boneOrganFormElement);

    Form mainForm = new Form("");
    mainForm.formElements.add(boneOrganFormElement);
    return mainForm;
  }

  public static List<Triple> sbDataTriples() {

    List<Triple> triples = new ArrayList<Triple>();
    triples.add(new Triple("subject", "obo:BFO_0000051", "measurementDatum"));
    triples
        .add(new MultiTriple("measurementDatum", "obo:IAO_0000136", "bonyPartSegment"));
    triples.add(new MultiTriple("bonyPartSegment", "obo-fma:regional_part_of",
        "bonyPart"));
    triples.add(new Triple("bonyPart", "obo-fma:constitutional_part_of", "object"));
    return triples;
  }

  public static List<Triple> sbSchemeTriples() {

    List<Triple> triples = new ArrayList<Triple>();
    // RestrictionTriples
    triples.add(new RestrictionTriple("skeletalInventoryType", "obo:BFO_0000051",
        new FormInputNode("measurementDatumType"), "owl:someValuesFrom"));
    triples.add(new RestrictionTriple(new FormInputNode("measurementDatumType"),
        "obo:IAO_0000136", "bonyPartSegmentType", "owl:allValuesFrom"));
    triples.add(new RestrictionTriple("bonyPartSegmentType", "obo-fma:regional_part_of",
        "bonyPartType", "owl:allValuesFrom"));
    triples.add(new RestrictionTriple("bonyPartType", "obo-fma:constitutional_part_of",
        new FormInputNode("boneOrganType"), "owl:someValuesFrom"));
    triples.add(new RestrictionTriple(new FormInputNode("boneOrganType"),
        "obo-fma:systemic_part_of", "skeletalRegionType", "owl:someValuesFrom"));
    triples.add(new RestrictionTriple("skeletalRegionType", "obo-fma:systemic_part_of",
        new MainInputNode("rangeUri"), "owl:someValuesFrom"));
    triples.addAll(sbTypeTriples());
    return triples;
  }

  public static List<Triple> sbTypeTriples() {
    List<Triple> triples = new ArrayList<Triple>();
    triples
        .add(new MultiTriple("object", "rdf:type", new FormInputNode("boneOrganType")));
    triples.add(new Triple("bonyPart", "rdf:type", "bonyPartType"));
    triples.add(new Triple("bonyPartSegment", "rdf:type", "bonyPartSegmentType"));
    triples.add(new Triple("measurementDatum", "rdf:type", "measurementDatumType"));
    triples.add(new Triple(new MainInputNode("subject"), "rdf:type",
        "skeletalInventoryType"));
    return triples;
  }

}
