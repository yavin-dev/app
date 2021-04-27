'use strict';

module.exports = function (environment) {
  let ENV = {
    modulePrefix: 'yavin-ui',
    environment,
    rootURL: '/ui/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        /*
         * Here you can enable experimental features on an ember canary build
         * e.g. EMBER_NATIVE_DECORATOR_SUPPORT: true
         */
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false,
      },
    },

    APP: {
      /*
       * Here you can pass flags/options to your application instance
       * when it is created
       */
    },

    navi: {
      dataEpoch: '1900-01-01',
      FEATURES: {
        enableDashboardFilters: true,
        exportFileTypes: ['csv'],
        enableTableEditing: true,
        enableTotals: true,
      },
    },
    apollo: {},
  };

  if (environment === 'development') {
    ENV['ember-cli-mirage'] = { enabled: true };

    /*
     * ENV.APP.LOG_RESOLVER = true;
     * ENV.APP.LOG_ACTIVE_GENERATION = true;
     * ENV.APP.LOG_TRANSITIONS = true;
     * ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
     * ENV.APP.LOG_VIEW_LOOKUPS = true;
     */
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
    ENV.APP.autoboot = false;
  }

  if (environment === 'production') {
    // here you can enable a production-specific feature
  }

  /*
   * ENVIRONMENT VARIABLE OVERRIDES
   */
  const { LOCATION_TYPE } = process.env;
  if (undefined !== LOCATION_TYPE) {
    ENV['locationType'] = LOCATION_TYPE;
  }

  const { ROOT_URL } = process.env;
  if (undefined !== ROOT_URL) {
    ENV['rootURL'] = ROOT_URL;
  }

  const { ENABLE_MOCKS } = process.env;
  if (undefined !== ENABLE_MOCKS) {
    const enabled = ENABLE_MOCKS === 'true';
    ENV['ember-cli-mirage'] = { enabled };
  }

  return ENV;
};
