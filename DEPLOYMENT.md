# Free Render test deployment

This repository includes a Render Blueprint for a temporary full-stack test
environment. It creates the React site, API gateway, eight supporting Spring
services, and a PostgreSQL database.

## Deploy

1. Review and commit only the application and deployment files you intend to
   publish. Do not commit local databases, logs, temporary files, or secrets.
2. Push that branch to the GitHub repository connected to your Render account.
3. In Render, choose **New > Blueprint**, select the repository and branch, and
   review `render.yaml` before applying it.
4. Wait for the services to finish building. Open the `smart-agri-frontend`
   static-site URL and use that URL for browser testing.

The Blueprint generates a JWT signing key and obtains PostgreSQL credentials
from the Render database; neither is stored in this repository. The Tavily key
is optional for startup but external search features need it. Ollama is not
deployed by this Blueprint: AI inference requires an Ollama endpoint reachable
from the farmer-agent service, configured with `SPRING_AI_OLLAMA_BASE_URL`.

## Free-plan limitations

This is for disposable testing, not production or real farmer data. Render's
free web services sleep after inactivity and can take about a minute to wake.
The nine backend web services share the workspace's free instance-hour quota.
The free PostgreSQL database is limited to 1 GB and expires after 30 days;
Render deletes its data after the expiry grace period. Back up any test data
you need to keep.

Render free web services cannot receive private-network traffic, so the
Blueprint connects the backend services through their public HTTPS hostnames.
The API gateway only allows the configured frontend origin and local
development origins.

Render deploys from a connected Git branch. It does not deploy uncommitted
files from this workstation. Check `git status` carefully before pushing: this
workspace contains pre-existing local source changes, generated databases,
logs, and temporary files that should not be included indiscriminately.
