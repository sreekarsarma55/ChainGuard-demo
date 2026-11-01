# ChainGuard demo

> **Deliberately vulnerable. Never build, run or deploy the code in this repository.** It exists to show a security gate blocking a build.

This repository is a test bed for [ChainGuard](https://github.com/sreekarsarma55/ChainGuard) (private for now), a B.Tech capstone project at Mohan Babu University. ChainGuard fuses several SBOM generators (Syft, cdxgen) and vulnerability scanners (Trivy, Grype) into one CI/CD gate:

1. It normalises what the generators found into one inventory.
2. It scans that inventory with every scanner, and correlates the findings.
3. It ranks each finding by EPSS, the CISA KEV catalog, CVSS, fix availability and dependency depth.
4. It decides PASS, WARN or FAIL against a written policy.

ChainGuard's source is not public yet.

## What happens here

- [`app/`](app/) is a small Java program that logs with **log4j-core 2.14.1**. That version is vulnerable to Log4Shell (CVE-2021-44228), which is in the CISA Known Exploited Vulnerabilities catalog.
- Every push and pull request runs [ChainGuard's GitHub Action](.github/workflows/chainguard.yml) on `app/`, with [`chainguard-policy.yaml`](chainguard-policy.yaml). That policy is ChainGuard's default: fail on anything in KEV or in the critical priority band, warn on high-priority findings that have a fix.
- **On `main`, the build fails.** The job summary lists the findings that fail the policy, with the versions that fix them. Code scanning (the **Security** tab) shows each one as an alert on the `pom.xml` line that declares the package.
- **The pull request that upgrades log4j-core passes.** Its code-scanning check shows the alerts it fixes.

## Who made this

B. Sanjay Ratna, T. R. Sreekar Sarma, K. Larish Reddy and S. A. Manaswini, with their guide Mrs. V. Jyothi, Department of Computer Science and Engineering, Mohan Babu University, Tirupati.
